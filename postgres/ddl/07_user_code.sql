CREATE TABLE IF NOT EXISTS fitness.user_code
(
    uuid uuid NOT NULL,
    code text COLLATE pg_catalog."default",
    CONSTRAINT user_code_pkey PRIMARY KEY (uuid),
    CONSTRAINT fkglrjgy3ykfawroe1ctvgdag7r FOREIGN KEY (uuid)
        REFERENCES fitness."user" (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)