package com.adjt.agendamento.core.usecase.especialidade;

import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.port.EspecialidadePort;

public class ObterPorIdEspecialidadeUseCase {

    private final EspecialidadePort<Especialidade> especialidadePort;

    private ObterPorIdEspecialidadeUseCase(EspecialidadePort<Especialidade> especialidadePort) {
        this.especialidadePort = especialidadePort;
    }

    public static ObterPorIdEspecialidadeUseCase create(EspecialidadePort<Especialidade> especialidadePort) {
        return new ObterPorIdEspecialidadeUseCase(especialidadePort);
    }

    public Especialidade run(Integer id) {
        return especialidadePort.obterPorId(id);
    }
}
