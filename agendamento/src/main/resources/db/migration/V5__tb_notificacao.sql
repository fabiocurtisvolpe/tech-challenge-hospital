-- =============================================================================
-- 1. TABELA PRINCIPAL: tb_notificacao
-- =============================================================================
CREATE TABLE IF NOT EXISTS tb_notificacao (
                                              id SERIAL PRIMARY KEY,
                                              data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                              consulta_id INTEGER NOT NULL,
                                              CONSTRAINT fk_notificacao_consulta FOREIGN KEY (consulta_id)
    REFERENCES tb_consulta(id) ON DELETE CASCADE
    );

-- =============================================================================
-- 2. TABELA DE AUDITORIA: tb_notificacao_aud (Hibernate Envers)
-- =============================================================================
CREATE TABLE IF NOT EXISTS tb_notificacao_aud (
                                                  id INTEGER NOT NULL,
                                                  rev INTEGER NOT NULL, -- ID da revisão vinculado à tabela revinfo
                                                  revtype SMALLINT NOT NULL, -- 0 (insert), 1 (update), 2 (delete)
                                                  data_hora TIMESTAMP,
                                                  consulta_id INTEGER,

                                                  PRIMARY KEY (id, rev),
    CONSTRAINT fk_notificacao_aud_revinfo FOREIGN KEY (rev)
    REFERENCES revinfo(rev)
    );