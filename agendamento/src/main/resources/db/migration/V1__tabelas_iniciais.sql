-- =============================================================================
-- 1. TABELAS PRINCIPAIS (NEGÓCIO)
-- =============================================================================

-- Tabela de Perfis (Roles)
-- A restrição UNIQUE no campo 'nome' garante que não haja repetição de perfis.
create table if not exists tb_perfil (
                                         id SERIAL primary key,
                                         nome VARCHAR(20) not null unique
    );

-- Tabela de Usuários
create table if not exists tb_usuario (
                                          id SERIAL primary key,
                                          nome VARCHAR(50) not null,
    email VARCHAR(50) not null unique,
    senha VARCHAR(255) not null,
    dt_criacao TIMESTAMP not null default CURRENT_TIMESTAMP
    );

-- Tabela de Relacionamento (Muitos para Muitos)
create table if not exists tb_usuario_perfil (
                                                 usuario_id INTEGER not null,
                                                 perfil_id INTEGER not null,
                                                 primary key (usuario_id,
                                                 perfil_id),
    constraint fk_usuario foreign key (usuario_id) references tb_usuario(id) on delete cascade,
    constraint fk_perfil foreign key (perfil_id) references tb_perfil(id) on delete cascade
    );

-- Tabela de Consultas (Histórico)
create table if not exists tb_consulta (
                                           id SERIAL primary key,
                                           data_hora TIMESTAMP not null,
                                           diagnostico TEXT,
                                           prescricao TEXT,
                                           paciente_id INTEGER not null,
                                           medico_id INTEGER,
                                           enfermeiro_id INTEGER,
                                           constraint fk_paciente foreign key (paciente_id) references tb_usuario(id),
    constraint fk_medico foreign key (medico_id) references tb_usuario(id),
    constraint fk_enfermeiro foreign key (enfermeiro_id) references tb_usuario(id)
    );

-- =============================================================================
-- 2. TABELAS DE AUDITORIA (HIBERNATE ENVERS)
-- =============================================================================
-- Tabela central de controle de revisões
create table if not exists revinfo (
                                       rev SERIAL primary key,
                                       revtstmp BIGINT
);

-- Auditoria de Perfis
create table if not exists tb_perfil_aud (
                                             id INTEGER not null,
                                             rev INTEGER not null,
                                             revtype smallint not null,
                                             nome VARCHAR(20),
    primary key (id,
                 rev),
    constraint fk_perfil_aud_revinfo foreign key (rev) references revinfo(rev)
    );

-- Auditoria de Usuarios
create table if not exists tb_usuario_aud (
                                              id INTEGER not null,
                                              rev INTEGER not null,
                                              revtype smallint not null,
                                              nome VARCHAR(50),
    email VARCHAR(50),
    senha VARCHAR(255),
    dt_criacao TIMESTAMP,
    primary key (id,
                 rev),
    constraint fk_usuario_aud_revinfo foreign key (rev) references revinfo(rev)
    );

-- Auditoria do Relacionamento Usuário-Perfil
create table if not exists tb_usuario_perfil_aud (
                                                     rev INTEGER not null,
                                                     usuario_id INTEGER not null,
                                                     perfil_id INTEGER not null,
                                                     revtype smallint not null,
                                                     primary key (rev,
                                                     usuario_id,
                                                     perfil_id),
    constraint fk_usuario_perfil_aud_revinfo foreign key (rev) references revinfo(rev)
    );

-- Auditoria de Consultas
create table if not exists tb_consulta_aud (
                                               id INTEGER not null,
                                               rev INTEGER not null,
                                               revtype smallint not null,
                                               data_hora TIMESTAMP,
                                               diagnostico TEXT,
                                               prescricao TEXT,
                                               paciente_id INTEGER,
                                               medico_id INTEGER,
                                               enfermeiro_id INTEGER,
                                               primary key (id,
                                               rev),
    constraint fk_consulta_aud_revinfo foreign key (rev) references revinfo(rev)
    );

-- =============================================================================
-- 3. CARGA DE DADOS INICIAL (SEED)
-- =============================================================================
-- Inserir os níveis de acesso (evita duplicidade com ON CONFLICT)
insert
into
    tb_perfil (nome)
values ('ROLE_MEDICO'),
       ('ROLE_ENFERMEIRO'),
       ('ROLE_PACIENTE'),
       ('ROLE_ADMIN')
    on
conflict (nome) do nothing;

-- Inserção de usuario (Senha: 123456 - Hash BCrypt)
INSERT INTO tb_usuario (nome, email, senha)
VALUES ('Administrador Sistema', 'admin@hospital.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DM99S6N6.S8.')
ON CONFLICT (email) DO NOTHING;

-- 2. Vincular o usuário ao perfil ROLE_ADMIN
-- ROLE_ADMIN que pode criar mérido e enfermeiro
INSERT INTO tb_usuario_perfil (usuario_id, perfil_id)
SELECT
    (SELECT id FROM tb_usuario WHERE email = 'admin@hospital.com'),
    (SELECT id FROM tb_perfil WHERE nome = 'ROLE_ADMIN')
    ON CONFLICT (usuario_id, perfil_id) DO NOTHING;