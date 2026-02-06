create table if not exists revinfo (
                                       rev SERIAL primary key,
                                       revtstmp BIGINT
);

-- =============================================================================
-- 1. TABELA PRINCIPAL: tb_pagamento
-- =============================================================================
CREATE TABLE IF NOT EXISTS tb_pagamento (
                                              id SERIAL PRIMARY KEY,
                                              dt_criacao TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,
                                              data_hora TIMESTAMP NOT NULL,
                                              consulta_id INTEGER NOT NULL,
                                              paciente_id INTEGER NOT NULL,
                                              valor NUMERIC(13, 2) NOT NULL,
                                              response_code INTEGER
    );

-- =============================================================================
-- 2. TABELA DE AUDITORIA: tb_pagamento_aud (Hibernate Envers)
-- =============================================================================
CREATE TABLE IF NOT EXISTS public.tb_pagamento_aud (
                                                       id INTEGER NOT NULL,
                                                       rev INTEGER NOT NULL, -- ID da revisão vinculado à tabela revinfo
                                                       revtype SMALLINT NOT NULL, -- 0 (insert), 1 (update), 2 (delete)
                                                       dt_criacao TIMESTAMP,
                                                       data_hora TIMESTAMP,
                                                       consulta_id INTEGER,
                                                       paciente_id INTEGER,
                                                       valor NUMERIC(13, 2),
    response_code INTEGER,
    CONSTRAINT tb_pagamento_aud_pkey PRIMARY KEY (id, rev),
    CONSTRAINT fk_pagamento_aud_revinfo FOREIGN KEY (rev) REFERENCES public.revinfo(rev)
    );