--liquibase formatted sql
--changeset guilherme.gps:EVENTS_TYPES

-- Evento
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('359478a8-f1f6-486e-bdc2-504bbead5fbc', true, 'INCLUSAO');
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('e2f4f40c-0340-4404-ad32-1a14692de709', true, 'ALTERACAO');
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('d3320a32-b803-40f9-ab35-13c29833f214', true, 'DESATIVACAO');
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('a6c4efd6-0407-4bfb-9400-dd6618b0b069', true, 'REMOCAO');
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('ca359094-7080-4d4e-b3b6-fd64f5ca7a71', true, 'VISUALIZACAO');

INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('7fc34510-0501-4768-beca-bd9345683a93', true, 'AUTENTICACAO');
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('3e2f3d03-42c5-4ae2-a240-114d5b332b4c', true, 'FALHA_AUTENTICACAO');
INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('eb37a61c-ce15-4b88-87f0-68b75911239f', true, 'ACESSO_NEGADO');

INSERT INTO START.tipo_evento (id, ativo, descricao) VALUES('8d8d76b8-b78b-407b-92c9-d5c452713c65', true, 'GEROU_ARQUIVO');