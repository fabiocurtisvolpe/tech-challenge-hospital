package com.adjt.pagamento.core.validator;

import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.util.MensagemUtil;

public class PagamentoValidator {

    public static void cadastarAtualizar(Pagamento pgto) {

        if (pgto.getIdConsulta() == null) {
            throw new IllegalArgumentException(MensagemUtil.ID_CONSULTA_VAZIO);
        }

        if (pgto.getIdPaciente() == null) {
            throw new IllegalArgumentException(MensagemUtil.ID_PACIENTE_VAZIO);
        }

        if (pgto.getValor() == null) {
            throw new IllegalArgumentException(MensagemUtil.VALOR_VAZIO);
        }
    }
}
