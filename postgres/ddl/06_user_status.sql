CREATE TABLE IF NOT EXISTS fitness.user_status
(
    user_uuid uuid NOT NULL,
    status_id text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (user_uuid),
    CONSTRAINT constrain_user FOREIGN KEY (user_uuid)
        REFERENCES fitness."user" (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT for_status FOREIGN KEY (status_id)
        REFERENCES fitness.status (status) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
