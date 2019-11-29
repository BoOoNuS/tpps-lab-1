DROP TABLE vectors;
DROP TABLE alternatives;
DROP TABLE criteria;
DROP TABLE comparing_types;
DROP TABLE marks;
DROP TABLE mark_types;

CREATE TABLE mark_types (
  id SERIAL NOT NULL,
  name CHARACTER VARYING(64) NOT NULL UNIQUE,
  unit CHARACTER VARYING(64) NOT NULL,
  value_type CHARACTER VARYING(64) NOT NULL,
  CONSTRAINT mark_type_id PRIMARY KEY (id)
);

CREATE TABLE marks (
  id SERIAL NOT NULL,
  name CHARACTER VARYING(64) NOT NULL UNIQUE,
  value CHARACTER VARYING(64),
  numeric_value INTEGER NOT NULL,
  mark_type_id INTEGER NOT NULL,
  CONSTRAINT mark_id PRIMARY KEY (id),
  CONSTRAINT marks_mark_type_id FOREIGN KEY (mark_type_id)
  REFERENCES mark_types (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE comparing_types (
  id SERIAL NOT NULL,
  name CHARACTER VARYING(64) NOT NULL UNIQUE,
  CONSTRAINT comparing_type_id PRIMARY KEY (id)
);

CREATE TABLE criteria (
id SERIAL NOT NULL,
name CHARACTER VARYING(64) NOT NULL,
weight INTEGER NOT NULL,
mark_type_id INTEGER NOT NULL,
mark_id INTEGER NOT NULL,
CONSTRAINT criteria_id PRIMARY KEY (id),
CONSTRAINT criteria_mark_type_id FOREIGN KEY (mark_type_id)
REFERENCES mark_types (id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE CASCADE,
CONSTRAINT mark_id FOREIGN KEY (mark_id)
REFERENCES marks (id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE alternatives (
  id SERIAL NOT NULL,
  name CHARACTER VARYING(64) NOT NULL UNIQUE,
  comparing_type_id INTEGER NOT NULL,
  CONSTRAINT alternative_id PRIMARY KEY (id),
  CONSTRAINT comparing_type_id FOREIGN KEY (comparing_type_id)
  REFERENCES comparing_types (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE vectors (
id SERIAL NOT NULL,
criteria_id INTEGER NOT NULL,
alternative_id INTEGER NOT NULL,
CONSTRAINT vector_id PRIMARY KEY (id),
CONSTRAINT criteria_id FOREIGN KEY (criteria_id)
REFERENCES criteria (id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE CASCADE,
CONSTRAINT alternative_id FOREIGN KEY (alternative_id)
REFERENCES alternatives (id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE CASCADE
);

