CREATE SCHEMA IF NOT EXISTS fitness
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS fitness.composition
(
    uuid uuid NOT NULL,
    weight integer,
    product_uuid uuid,
    CONSTRAINT composition_pkey1 PRIMARY KEY (uuid),
    CONSTRAINT product_uuid_constraint FOREIGN KEY (product_uuid)
        REFERENCES fitness.product (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)