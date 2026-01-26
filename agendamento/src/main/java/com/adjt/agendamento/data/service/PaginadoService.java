package com.adjt.agendamento.data.service;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.enums.FiltroOperadorEnum;
import com.adjt.agendamento.data.mapper.EntityMapper;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
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
        for (FilterDTO f : filters) {
            if (f.getOperador() == FiltroOperadorEnum.BETWEEN) {
                String[] valores = f.getValor().split(",");
                if (valores.length != 2) {
                    throw new IllegalArgumentException("Valor inválido para BETWEEN: " + f.getValor());
                }
            }
        }
    }

    private static <E> Specification<E> getESpecification(List<FilterDTO> filters) {
        return (root, query, cb) -> {
            // Se a lista de filtros estiver vazia, retorna uma consulta que não restringe nada.
            if (filters == null || filters.isEmpty()) {
                return cb.conjunction();
            }

            // Cria uma lista para armazenar cada condição (Predicate)
            List<jakarta.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();

            for (FilterDTO f : filters) {
                Path<?> path = getPath(root, f.getCampo());
                Class<?> type = path.getJavaType();

                jakarta.persistence.criteria.Predicate predicate = switch (f.getOperador()) {
                    case EQUALS -> cb.equal(path, parseValue(type, f.getValor()));
                    case NOT_EQUALS -> cb.notEqual(path, parseValue(type, f.getValor()));
                    case LIKE -> cb.like(cb.lower((Expression<String>) path), "%" + f.getValor().toLowerCase() + "%");
                    case GREATER_THAN -> cb.greaterThan((Expression<Comparable>) path, (Comparable) parseValue(type, f.getValor()));
                    case LESS_THAN -> cb.lessThan((Expression<Comparable>) path, (Comparable) parseValue(type, f.getValor()));
                    case GREATER_EQUAL -> cb.greaterThanOrEqualTo((Expression<Comparable>) path, (Comparable) parseValue(type, f.getValor()));
                    case LESS_EQUAL -> cb.lessThanOrEqualTo((Expression<Comparable>) path, (Comparable) parseValue(type, f.getValor()));
                    case BETWEEN -> {
                        String[] valores = f.getValor().split(",");
                        Comparable val1 = (Comparable) parseValue(type, valores[0]);
                        Comparable val2 = (Comparable) parseValue(type, valores[1]);
                        yield cb.between((Expression<Comparable>) path, val1, val2);
                    }
                };
                predicates.add(predicate);
            }

            // Combina todos os predicados da lista com um "AND"
            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }

    /**
     * Método auxiliar para resolver caminhos aninhados como "paciente.nome"
     */
    private static <E> Path<?> getPath(Root<E> root, String campo) {
        if (!campo.contains(".")) {
            return root.get(campo);
        }

        String[] parts = campo.split("\\.");
        Path<?> path = root;
        for (String part : parts) {
            path = path.get(part);
        }
        return path;
    }

    /**
     * Converte a String vinda do DTO para o tipo real do atributo da Entity
     */
    private static Object parseValue(Class<?> type, String value) {
        if (value == null || value.equalsIgnoreCase("null")) return null;

        if (type.equals(LocalDateTime.class)) return LocalDateTime.parse(value);
        if (type.equals(Integer.class) || type.equals(int.class)) return Integer.parseInt(value);
        if (type.equals(Long.class) || type.equals(long.class)) return Long.parseLong(long.class.getName());
        if (type.equals(Boolean.class) || type.equals(boolean.class)) return Boolean.parseBoolean(value);
        if (type.isEnum()) return Enum.valueOf((Class<Enum>) type, value);

        return value; // Retorna String por padrão
    }
}