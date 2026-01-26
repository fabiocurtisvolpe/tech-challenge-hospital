package com.adjt.agendamento.rest.config;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.port.EspecialidadePort;
import com.adjt.agendamento.core.port.PerfilPort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.usecase.consulta.*;
import com.adjt.agendamento.core.usecase.usuario.*;
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

    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(UsuarioPort<Usuario> usuarioPort,
                                                           PerfilPort<Perfil> perfilPort) {
        return CadastrarUsuarioUseCase.create(usuarioPort, perfilPort);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioPort<Usuario> usuarioPort,
                                                           PerfilPort<Perfil> perfilPort) {
        return AtualizarUsuarioUseCase.create(usuarioPort, perfilPort);
    }

    @Bean
    public ExcluirUsuarioUseCase excluirUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        return ExcluirUsuarioUseCase.create(usuarioPort);
    }

    @Bean
    public PaginadoUsuarioUseCase<Usuario> paginadoUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        return PaginadoUsuarioUseCase.create(usuarioPort);
    }

    @Bean
    public ObterPorIdConsultaUseCase obterPorIdConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                                               UsuarioPort<Usuario> usuarioPort) {
        return ObterPorIdConsultaUseCase.create(consultaPort, usuarioPort);
    }

    @Bean
    public CadastrarConsultaUseCase cadastrarConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                                             UsuarioPort<Usuario> usuarioPort,
                                                             EspecialidadePort<Especialidade> especialidadePort) {
        return CadastrarConsultaUseCase.create(consultaPort, usuarioPort, especialidadePort);
    }

    @Bean
    public AtualizarConsultaUseCase atualizarConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                                             UsuarioPort<Usuario> usuarioPort) {
        return AtualizarConsultaUseCase.create(consultaPort, usuarioPort);
    }

    @Bean
    public ExcluirConsultaUseCase excluirConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                                         UsuarioPort<Usuario> usuarioPort) {
        return ExcluirConsultaUseCase.create(consultaPort, usuarioPort);
    }

    @Bean
    public PaginadoConsultaUseCase<Consulta> paginadoConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                                                    UsuarioPort<Usuario> usuarioPort) {
        return PaginadoConsultaUseCase.create(consultaPort, usuarioPort);
    }
}
