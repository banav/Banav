/** Banco de Dados Offline */

CREATE TABLE offline.classe
(
  id bigint NOT NULL,
  nome character varying(255),
  datamovimentacao timestamp without time zone,
  ativo boolean,
  CONSTRAINT classe_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE offline.navio
(
  id bigint NOT NULL,
  nome character varying(255),
  datamovimentacao timestamp without time zone,
  ativo boolean,
  CONSTRAINT navio_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE offline.navio_classe
(
  classe bigint NOT NULL,
  navio bigint NOT NULL,
  quantidade integer,
  ativo boolean,
  datamovimentacao timestamp without time zone,
  CONSTRAINT pk_navio_classe PRIMARY KEY (classe, navio),
  CONSTRAINT fk_navio_classe_classe FOREIGN KEY (classe)
      REFERENCES offline.classe (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_navio_classe_navio FOREIGN KEY (navio)
      REFERENCES offline.navio (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE offline.porto
(
  id bigint NOT NULL,
  nome character varying(255),
  datamovimentacao timestamp without time zone,
  ativo boolean,
  CONSTRAINT porto_pkey PRIMARY KEY (id)

)
WITH (
OIDS=FALSE
);

ALTER TABLE  offline.usuario ADD COLUMN ativo boolean;
ALTER TABLE  offline.usuario ADD COLUMN datamovimentacao timestamp without time zone;
update offline.usuario  set datamovimentacao = current_timestamp where datamovimentacao is null;
update offline.usuario  set ativo = true where ativo is null;

CREATE TABLE offline.viagem
(
  id bigint NOT NULL,
  horachegada timestamp without time zone,
  horasaida timestamp without time zone,
  porto_destino bigint,
  navio_id bigint,
  porto_origem bigint,
  ativo boolean,
  datamovimentacao timestamp without time zone,
  CONSTRAINT viagem_pkey PRIMARY KEY (id),
  CONSTRAINT fk_viagem_navio FOREIGN KEY (navio_id)
  REFERENCES offline.navio (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_viagem_destino FOREIGN KEY (porto_destino)
  REFERENCES offline.porto (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_viagem_origem FOREIGN KEY (porto_origem)
  REFERENCES offline.porto (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);

CREATE TABLE offline.viagem_valor_classe
(
  id bigint NOT NULL,
  valor double precision,
  classe bigint,
  navio bigint,
  viagem_id bigint,
  aceita_gratuidade boolean NOT NULL,
  valor_meia double precision,
  ativo boolean,
  datamovimentacao timestamp without time zone,
  CONSTRAINT viagem_valor_classe_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vvc_viagem FOREIGN KEY (viagem_id)
  REFERENCES offline.viagem (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_vvc_navio_classe FOREIGN KEY (classe, navio)
  REFERENCES offline.navio_classe (classe, navio) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);

CREATE TABLE offline.passagem (
  id int8 NOT NULL,
  codigobarras varchar(255),
  gratuidade bool,
  valor float8,
  datamovimentacao timestamp,
  viagem_valor_classe_id int8,
  usuario_id int8 NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE offline.passagem
ADD FOREIGN KEY (viagem_valor_classe_id)
REFERENCES viagem_valor_classe ("id");

ALTER TABLE offline.passagem
ADD FOREIGN KEY (usuario_id)
REFERENCES usuario ("id");

CREATE SEQUENCE offline.seq_passagem INCREMENT BY 1 MINVALUE 1 NO MAXVALUE START WITH 1 CACHE 1 NO CYCLE;

/** Banco de Dados Online */

ALTER TABLE classe ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE classe ADD COLUMN ativo boolean;
update classe  set datamovimentacao = current_timestamp where datamovimentacao is null;
update classe  set ativo = true where ativo is null;

ALTER TABLE navio_classe ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE navio_classe ADD COLUMN ativo boolean;
update navio_classe  set datamovimentacao = current_timestamp where datamovimentacao is null;
update navio_classe  set ativo = true where ativo is null;

ALTER TABLE navio ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE navio ADD COLUMN ativo boolean;
update navio  set datamovimentacao = current_timestamp where datamovimentacao is null;
update navio  set ativo = true where ativo is null;

ALTER TABLE porto ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE porto ADD COLUMN ativo boolean;
update porto  set datamovimentacao = current_timestamp where datamovimentacao is null;
update porto  set ativo = true where ativo is null;

ALTER TABLE  usuario ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE  usuario ADD COLUMN ativo boolean;
update usuario set datamovimentacao = current_timestamp where datamovimentacao is null;
update usuario set ativo = true where ativo is null;

ALTER TABLE viagem ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE viagem ADD COLUMN ativo boolean;
update viagem  set datamovimentacao = current_timestamp where datamovimentacao is null;
update viagem  set ativo = true where ativo is null;

ALTER TABLE viagem_valor_classe ADD COLUMN datamovimentacao timestamp without time zone;
ALTER TABLE viagem_valor_classe ADD COLUMN ativo boolean;
update viagem_valor_classe  set datamovimentacao = current_timestamp where datamovimentacao is null;
update viagem_valor_classe  set ativo = true where ativo is null;