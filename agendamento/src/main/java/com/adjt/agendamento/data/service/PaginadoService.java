package com.adjt.agendamento.data.service;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.enums.FiltroOperadorEnum;
import com.adjt.agendamento.data.mapper.EntityMapper;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaginadoService<E, D> {

    private final JpaSpecificationExecutor<E> repository;
    private final EntityMapper<E, D> mapper;

    public PaginadoService(JpaSpecificationExecutor<E> repository, EntityMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ResultadoPaginacaoDTO<D> listarPaginado(int page, int size, List<FilterDTO> filters,
                                                   List<SortDTO> sorts) {

        validarFiltros(filters);

        Specification<E> spec = getESpecification(filters);

        Sort springSort = Sort.unsorted();
        for (SortDTO s : sorts) {
            springSort = springSort.and(Sort.by(
                    s.getOrdem() == SortDTO.Direction.ASC ? Sort.Direction.ASC : Sort.Direction.DESC,
                    s.getCampo()));
        }

        Page<E> result = repository.findAll(spec, PageRequest.of(page, size, springSort));

        List<D> content = result.getContent().stream().map(mapper::toModel).toList();

        return new ResultadoPaginacaoDTO<>(content, result.getNumber(), result.getSize(), result.getTotalElements());
    }

    private void validarFiltros(List<FilterDTO> filters) {
        if (filters == null) return;
        for (FilterDTO f : filters) {
            if (f.getOperador() == FiltroOperadorEnum.BETWEEN) {
                String[] valores = f.getValor().split(",");
                if (valores.length != 2) {
                    throw new IllegalArgumentException("Valor inválido para BETWEEN: " + f.getValor());
                }
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <E> Specification<E> getESpecification(List<FilterDTO> filters) {
        return (root, _, cb) -> {
            if (filters == null || filters.isEmpty()) {
                return cb.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            for (FilterDTO f : filters) {
                Path<?> path = getPath(root, f.getCampo());
                Class<?> type = path.getJavaType();
                Object parsedValue = parseValue(type, f.getValor());

                Predicate predicate = switch (f.getOperador()) {
                    case EQUALS -> cb.equal(path, parsedValue);
                    case NOT_EQUALS -> cb.notEqual(path, parsedValue);
                    case LIKE -> cb.like(cb.lower((Expression<String>) path), "%" + f.getValor().toLowerCase() + "%");
                    case GREATER_THAN -> cb.greaterThan((Expression<? extends Comparable>) path, (Comparable) parsedValue);
                    case LESS_THAN -> cb.lessThan((Expression<? extends Comparable>) path, (Comparable) parsedValue);
                    case GREATER_EQUAL -> cb.greaterThanOrEqualTo((Expression<? extends Comparable>) path, (Comparable) parsedValue);
                    case LESS_EQUAL -> cb.lessThanOrEqualTo((Expression<? extends Comparable>) path, (Comparable) parsedValue);
                    case BETWEEN -> {
                        String[] valores = f.getValor().split(",");
                        Comparable val1 = (Comparable) parseValue(type, valores[0]);
                        Comparable val2 = (Comparable) parseValue(type, valores[1]);
                        yield cb.between((Expression<? extends Comparable>) path, val1, val2);
                    }
                };
                predicates.add(predicate);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <E> Path<?> getPath(Root<E> root, String campo) {
        Path<?> path = root;
        if (campo.contains(".")) {
            for (String part : campo.split("\\.")) {
                path = path.get(part);
            }
        } else {
            path = root.get(campo);
        }
        return path;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object parseValue(Class<?> type, String value) {
        if (value == null || value.equalsIgnoreCase("null")) return null;

        if (type.equals(LocalDateTime.class)) return LocalDateTime.parse(value);
        if (type.equals(Integer.class) || type.equals(int.class)) return Integer.parseInt(value);
        if (type.equals(Long.class) || type.equals(long.class)) return Long.parseLong(value);
        if (type.equals(Boolean.class) || type.equals(boolean.class)) return Boolean.parseBoolean(value);
        if (type.isEnum()) return Enum.valueOf((Class<? extends Enum>) type, value);

        return value;
    }
}