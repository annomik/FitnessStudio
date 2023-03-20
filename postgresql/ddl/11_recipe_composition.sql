CREATE SCHEMA IF NOT EXISTS fitness
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS fitness.recipe_composition
(
    recipe_uuid uuid NOT NULL,
    composition_uuid uuid NOT NULL,
    CONSTRAINT constrain_composition FOREIGN KEY (composition_uuid)
        REFERENCES fitness.composition (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT constrain_recipe FOREIGN KEY (recipe_uuid)
        REFERENCES fitness.recipe (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)