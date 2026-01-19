package com.adjt.agendamento.core.dto;


import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
public class ResultadoPaginacaoDTO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 907682988992882263L;

    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;

    public ResultadoPaginacaoDTO(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

}
