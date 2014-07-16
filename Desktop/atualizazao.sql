create schema offline;

alter table checkin set schema offline;

CREATE TABLE offline.usuario
("id" int8 NOT NULL,
nome varchar(255) NOT NULL,
login varchar(255) NOT NULL,
senha varchar(255) NOT NULL,
perfil varchar(255) NOT NULL,
PRIMARY KEY ("id"));