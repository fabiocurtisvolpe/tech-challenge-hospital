-- =============================================================================
-- AJUSTE NA TABELA tb_perfil
-- =============================================================================

-- Adiciona dt_criacao na tabela principal se não existir
ALTER TABLE IF EXISTS tb_perfil
    ADD COLUMN IF NOT EXISTS dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;

-- =============================================================================
-- AJUSTE NA TABELA tb_perfil_aud (Auditoria Envers)
-- =============================================================================

-- Adiciona dt_criacao na tabela de auditoria se não existir
-- permitir que o Envers gerencie os estados históricos.
ALTER TABLE IF EXISTS tb_perfil_aud
    ADD COLUMN IF NOT EXISTS dt_criacao TIMESTAMP;