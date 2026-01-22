package com.adjt.agendamento.rest.controller;

import com.adjt.agendamento.core.usecase.usuario.ObterPorEmailUsuarioUseCase;
import com.adjt.agendamento.core.usecase.usuario.ObterPorIdUsuarioUseCase;
import com.adjt.agendamento.rest.dto.response.UsuarioResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @GetMapping
    public String getMessage() { return "Rota usuario"; }

    /*
    private final ObterPorIdUsuarioUseCase obterPorIdUsuarioUseCase;
    private final ObterPorEmailUsuarioUseCase obterPorEmailUsuarioUseCase;

    public UsuarioController(ObterPorIdUsuarioUseCase obterPorIdUsuarioUseCase, ObterPorEmailUsuarioUseCase obterPorEmailUsuarioUseCase) {
        this.obterPorIdUsuarioUseCase = obterPorIdUsuarioUseCase;
        this.obterPorEmailUsuarioUseCase = obterPorEmailUsuarioUseCase;
    }

    @GetMapping("/info")
    public UsuarioResponse buscarLogado() {
        //Usuario usuario = this.obterPorEmailUsuarioUseCase.run(UsuarioLogadoUtil.);
        return null;
    }

     */
}
