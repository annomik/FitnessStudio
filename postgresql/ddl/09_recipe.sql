CREATE TABLE IF NOT EXISTS fitness.recipe
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text NOT NULL,
    CONSTRAINT recipe_pkey PRIMARY KEY (uuid)
)