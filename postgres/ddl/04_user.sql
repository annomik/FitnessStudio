CREATE TABLE IF NOT EXISTS fitness."user"
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    fio text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (uuid),
    CONSTRAINT mailunic UNIQUE (mail)
)