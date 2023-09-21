package by.it_academy.jd2.MJD29522.fitness.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "fitness", name = "recipe")
public class RecipeEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "title")
    private String title;

    @ElementCollection
    @CollectionTable(
            schema ="fitness", name = "recipe_composition",
            joinColumns = @JoinColumn( name = "recipe_uuid"))
    private List<CompositionEntity> composition;
}
