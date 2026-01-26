package com.adjt.agendamento.core.validator;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.util.MensagemUtil;

public class PaginadoValidator {

    public static void validarPagina(int page, int size) {

        if (page < 0 || size <= 0) {
            throw new NotificacaoException(MensagemUtil.PAGINA_SIZE_INVALIDA);
        }
    }
}
