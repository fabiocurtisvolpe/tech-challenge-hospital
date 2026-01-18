-- =============================================================================
-- 1. TABELAS DE NEGÓCIO
-- =============================================================================

-- Tabela de Especialidades
CREATE TABLE IF NOT EXISTS tb_especialidade (
                                                id SERIAL PRIMARY KEY,
                                                data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(1000)
    );

-- Tabela de Relacionamento Médico (Usuário) e Especialidade
-- Nota: Assume-se que a tabela tb_usuario já exista conforme scripts anteriores
CREATE TABLE IF NOT EXISTS tb_medico_especialidade (
                                                       usuario_id INT NOT NULL,
                                                       especialidade_id INT NOT NULL,
                                                       PRIMARY KEY (usuario_id, especialidade_id),
    CONSTRAINT fk_medico_esp_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_medico_esp_especialidade FOREIGN KEY (especialidade_id) REFERENCES tb_especialidade(id) ON DELETE CASCADE
    );

-- =============================================================================
-- 2. TABELAS DE AUDITORIA (HIBERNATE ENVERS)
-- =============================================================================

-- Auditoria de Especialidades
CREATE TABLE IF NOT EXISTS tb_especialidade_aud (
                                                    id INTEGER NOT NULL,
                                                    rev INTEGER NOT NULL,
                                                    revtype SMALLINT NOT NULL,
                                                    nome VARCHAR(100),
    descricao VARCHAR(1000),
    data_hora TIMESTAMP,
    PRIMARY KEY (id, rev),
    CONSTRAINT fk_especialidade_aud_revinfo FOREIGN KEY (rev) REFERENCES revinfo(rev)
    );

-- Auditoria do Relacionamento Médico-Especialidade
CREATE TABLE IF NOT EXISTS tb_medico_especialidade_aud (
                                                           rev INTEGER NOT NULL,
                                                           usuario_id INTEGER NOT NULL,
                                                           especialidade_id INTEGER NOT NULL,
                                                           revtype SMALLINT NOT NULL,
                                                           PRIMARY KEY (rev, usuario_id, especialidade_id),
    CONSTRAINT fk_medico_esp_aud_revinfo FOREIGN KEY (rev) REFERENCES revinfo(rev)
    );

-- =============================================================================
-- 3. INSERÇÃO DE 20 ESPECIALIDADES MÉDICAS
-- =============================================================================

INSERT INTO tb_especialidade (nome, descricao) VALUES
                                                   ('Cardiologia', 'Diagnóstico e tratamento de doenças que atingem o coração e o sistema circulatório.'),
                                                   ('Dermatologia', 'Focada no diagnóstico e tratamento de doenças da pele, pelos, mucosas, cabelos e unhas.'),
                                                   ('Pediatria', 'Assistência a crianças e adolescentes em seus diversos aspectos, preventivos e curativos.'),
                                                   ('Ginecologia e Obstetrícia', 'Cuidado da saúde do aparelho reprodutor feminino e acompanhamento da gravidez e parto.'),
                                                   ('Ortopedia', 'Tratamento de doenças e deformidades dos ossos, músculos, articulações e ligamentos.'),
                                                   ('Neurologia', 'Tratamento de distúrbios estruturais do sistema nervoso central e periférico.'),
                                                   ('Psiquiatria', 'Lida com a prevenção, diagnóstico e tratamento de distúrbios mentais e emocionais.'),
                                                   ('Oftalmologia', 'Investigação e tratamento das doenças que acometem os olhos.'),
                                                   ('Otorrinolaringologia', 'Cuida das doenças dos ouvidos, nariz e garganta.'),
                                                   ('Endocrinologia', 'Tratamento das glândulas endócrinas e suas doenças metabólicas e hormonais.'),
                                                   ('Urologia', 'Tratamento do trato urinário de homens e mulheres e do sistema reprodutor masculino.'),
                                                   ('Gastroenterologia', 'Focada no trato digestivo (esôfago, estômago, intestinos, fígado e pâncreas).'),
                                                   ('Infectologia', 'Diagnóstico e tratamento de doenças causadas por vírus, bactérias e parasitas.'),
                                                   ('Nefrologia', 'Especialidade médica que se ocupa do diagnóstico e tratamento clínico das doenças do rim.'),
                                                   ('Pneumologia', 'Responsável pelo tratamento das doenças das vias aéreas inferiores e pulmões.'),
                                                   ('Reumatologia', 'Diagnóstico e tratamento de doenças que afetam o tecido conjuntivo e articulações.'),
                                                   ('Geriatria', 'Focada na prevenção e no tratamento de doenças e incapacidades em idosos.'),
                                                   ('Oncologia', 'Especialidade que estuda e trata os tumores (neoplasias), especialmente os malignos.'),
                                                   ('Hematologia', 'Estudo do sangue e de seus elementos constituintes e doenças relacionadas.'),
                                                   ('Anestesiologia', 'Estudo e aplicação de anestesia para procedimentos cirúrgicos e alívio da dor.')
    ON CONFLICT (nome) DO NOTHING;