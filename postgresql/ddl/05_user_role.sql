CREATE TABLE IF NOT EXISTS fitness.user_role
(
    user_uuid uuid NOT NULL,
    role_id text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_uuid),
    CONSTRAINT constrain_user FOREIGN KEY (user_uuid)
        REFERENCES fitness."user" (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT for_role_id FOREIGN KEY (role_id)
        REFERENCES fitness.role (role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
