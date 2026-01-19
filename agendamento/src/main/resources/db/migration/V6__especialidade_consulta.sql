-- =============================================================================
-- 1. AJUSTE NA TABELA tb_consulta
-- =============================================================================

-- Adiciona a coluna se não existir
ALTER TABLE IF EXISTS public.tb_consulta
    ADD COLUMN IF NOT EXISTS especialidade_id int4;

-- Adiciona a Constraint de Chave Estrangeira (FK)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_consulta_especialidade') THEN
ALTER TABLE public.tb_consulta
    ADD CONSTRAINT fk_consulta_especialidade
        FOREIGN KEY (especialidade_id) REFERENCES public.tb_especialidade(id);
END IF;
END;
$$;


-- =============================================================================
-- 2. AJUSTE NA TABELA tb_consulta_aud (Auditoria Envers)
-- =============================================================================

-- Adiciona a coluna na tabela de auditoria se não existir
ALTER TABLE IF EXISTS public.tb_consulta_aud
    ADD COLUMN IF NOT EXISTS especialidade_id int4;