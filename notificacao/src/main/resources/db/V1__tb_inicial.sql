create table if not exists revinfo (
                                       rev SERIAL primary key,
                                       revtstmp BIGINT
);

-- =============================================================================
-- 1. TABELA PRINCIPAL: tb_notificacao
-- =============================================================================
CREATE TABLE IF NOT EXISTS tb_notificacao (
                                              id SERIAL PRIMARY KEY,
                                              dt_criacao TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,
                                              data_hora TIMESTAMP NOT NULL,
                                              nome_paciente VARCHAR(50) NOT NULL,
                                              telefone_paciente VARCHAR(20) NOT NULL,
                                              email_paciente VARCHAR(50) NOT NULL unique,
                                              nome_medico VARCHAR(50) NOT NULL,
                                              especialidade VARCHAR(100) NOT NULL
    );

-- =============================================================================
-- 2. TABELA DE AUDITORIA: tb_notificacao_aud (Hibernate Envers)
-- =============================================================================
CREATE TABLE IF NOT EXISTS tb_notificacao_aud (
                                                  id INTEGER NOT NULL,
                                                  rev INTEGER NOT NULL, -- ID da revisão vinculado à tabela revinfo
                                                  revtype SMALLINT NOT NULL, -- 0 (insert), 1 (update), 2 (delete)
                                                  dt_criacao TIMESTAMP,
                                                  data_hora TIMESTAMP,
                                                  nome_paciente VARCHAR(50),
                                                  telefone_paciente VARCHAR(20),
                                                  email_paciente VARCHAR(50),
                                                  nome_medico VARCHAR(50),
                                                  especialidade VARCHAR(100),
                                                  PRIMARY KEY (id, rev),
    CONSTRAINT fk_notificacao_aud_revinfo FOREIGN KEY (rev)
    REFERENCES revinfo(rev)
    );