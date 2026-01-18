-- =============================================================================
-- AJUSTE NA TABELA tb_usuario E tb_usuario_aud
-- =============================================================================

-- Verifica se a tabela existe e adiciona a coluna apenas se ela NÃO existir
ALTER TABLE IF EXISTS tb_usuario
    ADD COLUMN IF NOT EXISTS ativo BOOLEAN NOT NULL DEFAULT TRUE;

-- Para a tabela de auditoria (Envers)
ALTER TABLE IF EXISTS tb_usuario_aud
    ADD COLUMN IF NOT EXISTS ativo BOOLEAN;


-- =============================================================================
-- AJUSTE NA TABELA tb_especialidade E tb_especialidade_aud
-- =============================================================================

-- Verifica se a tabela existe e adiciona a coluna apenas se ela NÃO existir
ALTER TABLE IF EXISTS tb_especialidade
    ADD COLUMN IF NOT EXISTS ativo BOOLEAN NOT NULL DEFAULT TRUE;

-- Para a tabela de auditoria (Envers)
ALTER TABLE IF EXISTS tb_especialidade_aud
    ADD COLUMN IF NOT EXISTS ativo BOOLEAN;