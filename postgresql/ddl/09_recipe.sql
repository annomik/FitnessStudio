CREATE SCHEMA IF NOT EXISTS fitness
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS fitness.recipe
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    title text NOT NULL,
    CONSTRAINT recipe_pkey PRIMARY KEY (uuid)
)