package com.adjt.agendamento.rest.controller;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.usecase.usuario.CadastrarUsuarioUseCase;
import com.adjt.agendamento.core.usecase.usuario.ObterPorEmailUsuarioUseCase;
import com.adjt.agendamento.core.usecase.usuario.ObterPorIdUsuarioUseCase;
import com.adjt.agendamento.rest.dto.request.UsuarioRequest;
import com.adjt.agendamento.rest.dto.response.UsuarioResponse;
import com.adjt.agendamento.rest.mapper.UsuarioRestMapper;
import com.adjt.agendamento.rest.security.util.UsuarioLogadoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRestMapper usuarioRestMapper;

    private final ObterPorIdUsuarioUseCase obterPorIdUsuarioUseCase;
    private final ObterPorEmailUsuarioUseCase obterPorEmailUsuarioUseCase;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public UsuarioController(UsuarioRestMapper usuarioRestMapper,
                             ObterPorIdUsuarioUseCase obterPorIdUsuarioUseCase,
                             ObterPorEmailUsuarioUseCase obterPorEmailUsuarioUseCase,
                             CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {

        this.usuarioRestMapper = usuarioRestMapper;
        this.obterPorIdUsuarioUseCase = obterPorIdUsuarioUseCase;
        this.obterPorEmailUsuarioUseCase = obterPorEmailUsuarioUseCase;
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    private String gerarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {

        String senhaCrypt = gerarSenha(usuarioRequest.getSenha());
        usuarioRequest.setSenha(senhaCrypt);
        Usuario usuario = this.usuarioRestMapper.toModel(usuarioRequest);

        Usuario resp = this.cadastrarUsuarioUseCase.run(usuario, usuarioRequest.getPerfisIds(), UsuarioLogadoUtil.getUsuarioLogado());

        return ResponseEntity.ok(usuarioRestMapper.toResponse(resp));
    }

    @GetMapping("/info")
    public UsuarioResponse buscarLogado() {
        //Usuario usuario = this.obterPorEmailUsuarioUseCase.run(UsuarioLogadoUtil.);
        return null;
    }
}
