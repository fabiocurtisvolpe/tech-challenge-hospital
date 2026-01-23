package com.adjt.agendamento.rest.controller;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.usecase.usuario.*;
import com.adjt.agendamento.rest.dto.request.PaginacaoRequest;
import com.adjt.agendamento.rest.dto.request.UsuarioRequest;
import com.adjt.agendamento.rest.dto.response.UsuarioResponse;
import com.adjt.agendamento.rest.mapper.UsuarioRestMapper;
import com.adjt.agendamento.rest.security.util.UsuarioLogadoUtil;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRestMapper usuarioRestMapper;

    private final ObterPorIdUsuarioUseCase obterPorIdUsuarioUseCase;
    private final ObterPorEmailUsuarioUseCase obterPorEmailUsuarioUseCase;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;
    private final PaginadoUsuarioUseCase<Usuario> paginadoUsuarioUseCase;

    public UsuarioController(UsuarioRestMapper usuarioRestMapper,
                             ObterPorIdUsuarioUseCase obterPorIdUsuarioUseCase,
                             ObterPorEmailUsuarioUseCase obterPorEmailUsuarioUseCase,
                             CadastrarUsuarioUseCase cadastrarUsuarioUseCase,
                             AtualizarUsuarioUseCase atualizarUsuarioUseCase,
                             ExcluirUsuarioUseCase excluirUsuarioUseCase,
                             PaginadoUsuarioUseCase<Usuario> paginadoUsuarioUseCase) {

        this.usuarioRestMapper = usuarioRestMapper;
        this.obterPorIdUsuarioUseCase = obterPorIdUsuarioUseCase;
        this.obterPorEmailUsuarioUseCase = obterPorEmailUsuarioUseCase;
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.excluirUsuarioUseCase = excluirUsuarioUseCase;
        this.paginadoUsuarioUseCase = paginadoUsuarioUseCase;
    }

    private String gerarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    @PostMapping
    public UsuarioResponse cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        String senhaCrypt = gerarSenha(usuarioRequest.getSenha());
        usuarioRequest.setSenha(senhaCrypt);

        Usuario usuario = this.usuarioRestMapper.toModel(usuarioRequest);
        Usuario resp = this.cadastrarUsuarioUseCase.run(usuario, usuarioRequest.getPerfisIds(), UsuarioLogadoUtil.getUsuarioLogado());

        return usuarioRestMapper.toResponse(resp);
    }

    @PutMapping
    public UsuarioResponse atualizar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        String senhaCrypt = gerarSenha(usuarioRequest.getSenha());
        usuarioRequest.setSenha(senhaCrypt);

        Usuario usuario = this.usuarioRestMapper.toModel(usuarioRequest);
        Usuario resp = this.atualizarUsuarioUseCase.run(usuario, usuarioRequest.getPerfisIds(), UsuarioLogadoUtil.getUsuarioLogado());

        return usuarioRestMapper.toResponse(resp);
    }

    @GetMapping("/{id}")
    public UsuarioResponse obter(@PathVariable @Valid Integer id) {

        Usuario resp = this.obterPorIdUsuarioUseCase.run(id,  UsuarioLogadoUtil.getUsuarioLogado());
        return usuarioRestMapper.toResponse(resp);
    }

    @DeleteMapping("/{id}")
    public Boolean excluir(@PathVariable Integer id) {
        return this.excluirUsuarioUseCase.run(id,  UsuarioLogadoUtil.getUsuarioLogado());
    }

    @PostMapping("/paginado")
    public ResultadoPaginacaoDTO<UsuarioResponse> paginado(@RequestBody @Valid PaginacaoRequest paginacao) {

        ResultadoPaginacaoDTO<Usuario> resultado = this.paginadoUsuarioUseCase.run(
                paginacao.getPagina(),
                paginacao.getQtdPagina(),
                paginacao.getFiltros(),
                paginacao.getOrdenacao(),
                UsuarioLogadoUtil.getUsuarioLogado());

        return new ResultadoPaginacaoDTO<>(
                resultado.getContent().stream()
                        .map(this.usuarioRestMapper::toResponse)
                        .toList(),
                resultado.getPageNumber(),
                resultado.getPageSize(),
                resultado.getTotalElements());
    }

    @GetMapping("/info")
    public UsuarioResponse buscarLogado() {
        Usuario resp = this.obterPorEmailUsuarioUseCase.run(UsuarioLogadoUtil.getUsuarioLogado());
        return usuarioRestMapper.toResponse(resp);
    }
}
