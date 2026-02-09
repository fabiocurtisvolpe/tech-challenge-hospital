DROP VIEW IF EXISTS public.vw_consulta_historico;

CREATE VIEW public.vw_consulta_historico AS
SELECT
    aud.id,
    aud.rev,
    aud.revtype,
    aud.data_hora,
    aud.diagnostico,
    aud.prescricao,
    aud.dt_criacao,
    aud.status,
    aud.valor,
    p.nome AS paciente_nome,
    p.email AS paciente_email,
    m.nome AS medico_nome,
    e.nome AS especialidade_nome
FROM
    public.tb_consulta_aud aud
        LEFT JOIN public.tb_usuario p ON aud.paciente_id = p.id
        LEFT JOIN public.tb_usuario m ON aud.medico_id = m.id
        LEFT JOIN public.tb_especialidade e ON aud.especialidade_id = e.id;