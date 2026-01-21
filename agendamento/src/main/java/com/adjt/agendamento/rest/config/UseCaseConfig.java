package com.adjt.agendamento.rest.config;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.usecase.usuario.ObterPorEmailUsuarioUseCase;
import com.adjt.agendamento.core.usecase.usuario.ObterPorIdUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ObterPorEmailUsuarioUseCase obterUsuarioPorEmailUseCase(UsuarioPort<Usuario> usuarioPort) {
        return ObterPorEmailUsuarioUseCase.create(usuarioPort);
    }

    @Bean
    public ObterPorIdUsuarioUseCase obterUsuarioPorIdUseCase(UsuarioPort<Usuario> usuarioPort) {
        return ObterPorIdUsuarioUseCase.create(usuarioPort);
    }
}
