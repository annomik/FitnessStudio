CREATE TABLE IF NOT EXISTS fitness.status
(
    status text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (status)
)
