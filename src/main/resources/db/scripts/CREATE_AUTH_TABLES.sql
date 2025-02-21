--liquibase formatted sql
--changeset guilherme.gps:CREATE_AUTH_TABLES
CREATE TABLE START.perfil (
	id UUID DEFAULT RANDOM_UUID() NOT NULL,
	codigo INT GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	ativo bool DEFAULT true NOT NULL,
	descricao varchar(50) NOT NULL,
	CONSTRAINT pk_perfil PRIMARY KEY (id)
);

CREATE TABLE START.usuario (
	id UUID DEFAULT RANDOM_UUID() NOT NULL,
	codigo INT GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	ativo bool DEFAULT true NOT NULL,
	id_perfil UUID NULL,
	login varchar(50) NOT NULL UNIQUE,
	nome varchar(100) NOT NULL,
	cpf varchar(11) NOT NULL UNIQUE,
	senha varchar(255) NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_usuario PRIMARY KEY (id),
	CONSTRAINT fk_usuario_perfil FOREIGN KEY (id_perfil) REFERENCES START.perfil(id)
);

CREATE TABLE START.tipo_evento (
	id UUID DEFAULT RANDOM_UUID() NOT NULL,
	codigo INT GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	ativo bool DEFAULT true NOT NULL,
	descricao varchar(60) NOT NULL,
	CONSTRAINT pk_tipo_evento PRIMARY KEY (id)
);

CREATE TABLE START.evento (
	id UUID DEFAULT RANDOM_UUID() NOT NULL,
	codigo INT GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	ativo bool DEFAULT true NOT NULL,
	dt_registro timestamp DEFAULT now() NOT NULL,
	id_usuario UUID NOT NULL,
	id_tipo_evento UUID NOT NULL,
	descricao varchar(255) NULL,
	ip_usuario varchar(40) NULL,
	CONSTRAINT pk_evento PRIMARY KEY (id),
	CONSTRAINT fk_evento_tipo_evento FOREIGN KEY (id_tipo_evento) REFERENCES START.tipo_evento(id),
	CONSTRAINT fk_evento_usuario FOREIGN KEY (id_usuario) REFERENCES START.usuario(id)
);