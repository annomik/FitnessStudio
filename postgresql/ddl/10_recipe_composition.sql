CREATE SCHEMA IF NOT EXISTS fitness
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS fitness.recipe_composition
(
    recipe_uuid uuid NOT NULL,
    product_uuid uuid NOT NULL,
    weight integer NOT NULL,
    CONSTRAINT constrain_product FOREIGN KEY (product_uuid)
        REFERENCES fitness.product (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT constrain_recipe FOREIGN KEY (recipe_uuid)
        REFERENCES fitness.recipe (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)