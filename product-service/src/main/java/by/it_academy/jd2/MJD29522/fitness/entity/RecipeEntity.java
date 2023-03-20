package by.it_academy.jd2.MJD29522.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "recipe",
        uniqueConstraints = @UniqueConstraint(columnNames = "uuid"))
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

    @Column(name = "composition")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            //(cascade = CascadeType.MERGE)
    @JoinTable(
            schema = "fitness", name = "recipe_composition",
            joinColumns = @JoinColumn( name = "recipe_uuid", referencedColumnName="uuid"),
            inverseJoinColumns= @JoinColumn(name="composition_uuid", referencedColumnName="uuid")
    )
    private List<CompositionEntity> composition;

    public RecipeEntity() {
    }

    public RecipeEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                        String title, List<CompositionEntity> composition) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CompositionEntity> getComposition() {
        return composition;
    }

    public void setComposition(List<CompositionEntity> composition) {
        this.composition = composition;
    }


}
