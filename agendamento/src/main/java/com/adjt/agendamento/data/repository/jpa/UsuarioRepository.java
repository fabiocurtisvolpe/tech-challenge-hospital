package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>,
        JpaSpecificationExecutor<UsuarioEntity> {

    Optional<UsuarioEntity> obterPorId(Integer id);
    Optional<UsuarioEntity> obterPorNome(String nome);
    Optional<UsuarioEntity> obterPorEmail(String email);
}
