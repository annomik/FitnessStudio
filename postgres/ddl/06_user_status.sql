CREATE TABLE IF NOT EXISTS fitness.user_status
(
    user_uuid uuid NOT NULL,
    status_id text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (user_uuid),
    CONSTRAINT fkqvr7pkk41lhosrxya72hy5w0l FOREIGN KEY (user_uuid)
        REFERENCES fitness."user" (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT forstatus FOREIGN KEY (status_id)
        REFERENCES fitness.status (status) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
