CREATE TABLE IF NOT EXISTS fitness.user_code
(
    uuid uuid NOT NULL,
    code text COLLATE pg_catalog."default",
    CONSTRAINT user_code_pkey PRIMARY KEY (uuid),
    CONSTRAINT constrain_uuid FOREIGN KEY (uuid)
        REFERENCES fitness."user" (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)