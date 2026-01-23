package com.adjt.agendamento.rest.dto.request;

import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PaginacaoRequest {

    @Min(0)
    private int pagina;

    @Min(5)
    private int qtdPagina;

    private List<FilterDTO> filtros;

    private List<SortDTO> ordenacao;

    public PaginacaoRequest() {
    }
}
