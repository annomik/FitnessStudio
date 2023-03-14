CREATE TABLE IF NOT EXISTS fitness.composition
(
    uuid uuid NOT NULL,
    weight integer,
    product_uuid uuid,
    CONSTRAINT composition_pkey1 PRIMARY KEY (uuid),
    CONSTRAINT fkfbchfkak0b83eox5jt0547fhi FOREIGN KEY (product_uuid)
        REFERENCES fitness.product (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)