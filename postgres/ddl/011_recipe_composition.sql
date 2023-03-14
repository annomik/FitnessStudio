CREATE TABLE IF NOT EXISTS fitness.recipe_composition
(
    recipe_uuid uuid NOT NULL,
    composition_uuid uuid NOT NULL,
    CONSTRAINT fk8y8qr1x9gvnn6eesodo4sx2d5 FOREIGN KEY (recipe_uuid)
        REFERENCES fitness.recipe (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkp8rloo57kj6ouqwlkd9hlcthy FOREIGN KEY (composition_uuid)
        REFERENCES fitness.composition (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)