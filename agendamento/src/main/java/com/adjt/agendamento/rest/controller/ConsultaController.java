package com.adjt.agendamento.rest.controller;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.usecase.consulta.*;
import com.adjt.agendamento.rest.dto.request.ConsultaRequest;
import com.adjt.agendamento.rest.dto.request.UsuarioRequest;
import com.adjt.agendamento.rest.dto.response.ConsultaResponse;
import com.adjt.agendamento.rest.dto.response.UsuarioResponse;
import com.adjt.agendamento.rest.mapper.ConsultaRestMapper;
import com.adjt.agendamento.rest.security.util.UsuarioLogadoUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    private final ConsultaRestMapper consultaRestMapper;

    private final AtualizarConsultaUseCase atualizarConsultaUseCase;
    private final CadastrarConsultaUseCase cadastrarConsultaUseCase;
    private final ExcluirConsultaUseCase excluirConsultaUseCase;
    private final ObterPorIdConsultaUseCase obterPorIdConsultaUseCase;
    private final PaginadoConsultaUseCase<Consulta> paginadoConsultaUseCase;

    public ConsultaController(ConsultaRestMapper consultaRestMapper,
                              AtualizarConsultaUseCase atualizarConsultaUseCase,
                              CadastrarConsultaUseCase cadastrarConsultaUseCase,
                              ExcluirConsultaUseCase excluirConsultaUseCase,
                              ObterPorIdConsultaUseCase obterPorIdConsultaUseCase,
                              PaginadoConsultaUseCase<Consulta> paginadoConsultaUseCase) {
        this.consultaRestMapper = consultaRestMapper;
        this.atualizarConsultaUseCase = atualizarConsultaUseCase;
        this.cadastrarConsultaUseCase = cadastrarConsultaUseCase;
        this.excluirConsultaUseCase = excluirConsultaUseCase;
        this.obterPorIdConsultaUseCase = obterPorIdConsultaUseCase;
        this.paginadoConsultaUseCase = paginadoConsultaUseCase;
    }

    @PostMapping
    public ConsultaResponse cadastrar(@RequestBody @Valid ConsultaRequest consultaRequest) {

        Consulta consulta = this.consultaRestMapper.toModel(consultaRequest);

        Integer pacienteId = consultaRequest.getPacienteId();
        Integer medicadoId = consultaRequest.getMedicadoId();
        Integer enfermeiroId  = consultaRequest.getEnfermeiroId();
        Integer especialidadeId  = consultaRequest.getEspecialidadeId();

        Consulta resp = this.cadastrarConsultaUseCase.run(consulta,
                pacienteId, medicadoId, enfermeiroId, especialidadeId,
                UsuarioLogadoUtil.getUsuarioLogado());

        return consultaRestMapper.toResponse(resp);
    }

    @PutMapping
    public ConsultaResponse atualizar(@RequestBody @Valid ConsultaRequest consultaRequest) {

        Consulta consulta = this.consultaRestMapper.toModel(consultaRequest);

        Integer pacienteId = consultaRequest.getPacienteId();
        Integer medicadoId = consultaRequest.getMedicadoId();
        Integer enfermeiroId  = consultaRequest.getEnfermeiroId();
        Integer especialidadeId  = consultaRequest.getEspecialidadeId();

        Consulta resp = this.atualizarConsultaUseCase.run(consulta,
                pacienteId, medicadoId, enfermeiroId, especialidadeId,
                UsuarioLogadoUtil.getUsuarioLogado());

        return consultaRestMapper.toResponse(resp);
    }
}
