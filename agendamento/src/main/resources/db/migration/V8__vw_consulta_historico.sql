create or replace
view public.vw_consulta_historico as
select
    aud.id,
    aud.rev,
    aud.revtype,
    aud.data_hora,
    aud.diagnostico,
    aud.prescricao,
    aud.dt_criacao,
    p.nome as paciente_nome,
    p.email as paciente_email,
    m.nome as medico_nome,
    e.nome as especialidade_nome
from
    public.tb_consulta_aud aud
        left join public.tb_usuario p on
        aud.paciente_id = p.id
        left join public.tb_usuario m on
        aud.medico_id = m.id
        left join public.tb_especialidade e on
        aud.especialidade_id = e.id;