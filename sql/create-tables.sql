DROP TABLE vectors;
DROP TABLE alternatives;
DROP TABLE criteria;
DROP TABLE comparing_types;
DROP TABLE marks;
DROP TABLE mark_types;

CREATE TABLE mark_types (
  id         SERIAL                NOT NULL,
  name       CHARACTER VARYING(64) NOT NULL UNIQUE,
  unit       CHARACTER VARYING(64) NOT NULL,
  value_type CHARACTER VARYING(64) NOT NULL,
  CONSTRAINT mark_type_id PRIMARY KEY (id)
);

CREATE TABLE marks (
  id            SERIAL  NOT NULL,
  value         CHARACTER VARYING(64) UNIQUE,
  numeric_value INTEGER NOT NULL,
  mark_type_id  INTEGER NOT NULL,
  CONSTRAINT mark_id PRIMARY KEY (id),
  CONSTRAINT marks_mark_type_id FOREIGN KEY (mark_type_id)
  REFERENCES mark_types (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE comparing_types (
  id   SERIAL                NOT NULL,
  name CHARACTER VARYING(64) NOT NULL UNIQUE,
  CONSTRAINT comparing_type_id PRIMARY KEY (id)
);

CREATE TABLE criteria (
  id                SERIAL                NOT NULL,
  name              CHARACTER VARYING(64) NOT NULL UNIQUE,
  weight            INTEGER               NOT NULL,
  mark_type_id      INTEGER               NOT NULL,
  comparing_type_id INTEGER               NOT NULL,
  CONSTRAINT criterion_comparing_type_id FOREIGN KEY (comparing_type_id)
  REFERENCES comparing_types (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT criteria_id PRIMARY KEY (id),
  CONSTRAINT criteria_mark_type_id FOREIGN KEY (mark_type_id)
  REFERENCES mark_types (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE alternatives (
  id                SERIAL                NOT NULL,
  name              CHARACTER VARYING(64) NOT NULL UNIQUE,
  comparing_type_id INTEGER               NOT NULL,
  CONSTRAINT alternative_id PRIMARY KEY (id),
  CONSTRAINT alternative_comparing_type_id FOREIGN KEY (comparing_type_id)
  REFERENCES comparing_types (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE vectors (
  id             SERIAL  NOT NULL,
  criterion_id   INTEGER NOT NULL,
  alternative_id INTEGER NOT NULL,
  mark_id        INTEGER NOT NULL,
  CONSTRAINT mark_id FOREIGN KEY (mark_id)
  REFERENCES marks (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT vector_id PRIMARY KEY (id),
  CONSTRAINT criterion_id FOREIGN KEY (criterion_id)
  REFERENCES criteria (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT alternative_id FOREIGN KEY (alternative_id)
  REFERENCES alternatives (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

