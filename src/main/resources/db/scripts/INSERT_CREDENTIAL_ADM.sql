--liquibase formatted sql
--changeset guilherme.gps:INSERT_CREDENTIAL_ADM
-- Perfis
INSERT INTO "START".PERFIL (ID, ATIVO, DESCRICAO) VALUES('f0c8362a-2915-48d4-8511-a1ddb909dfb1', true, 'ADM');

-- Usuarios
INSERT INTO "START".USUARIO (ID_PERFIL, LOGIN, NOME, CPF, SENHA, EMAIL) VALUES('f0c8362a-2915-48d4-8511-a1ddb909dfb1', 'admin', 'System Administrator', '12345678909', '$2a$10$86t8a8327OoRTLljQ6Cqsu0PIVGyW06y8lib0sxJe0icHyQCKiXqG', 'admin@system.com');