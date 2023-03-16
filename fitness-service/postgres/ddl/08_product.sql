CREATE TABLE IF NOT EXISTS fitness.product
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text COLLATE pg_catalog."default" NOT NULL,
    weight bigint NOT NULL,
    calories bigint NOT NULL,
    proteins numeric NOT NULL,
    fats numeric NOT NULL,
    carbohydrates numeric NOT NULL,
    CONSTRAINT product1_pkey PRIMARY KEY (uuid)
)