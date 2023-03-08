package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import by.it_academy.jd2.MJD29522.fitness.serializers.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RecipeDTO {

    private UUID uuid;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;

    private String title;

    //private RecipeCreateDTO recipeCreateDTO;

    private List<CompositionWithAllParametersDTO> composition;

    public RecipeDTO() {
    }

    public RecipeDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                     String title, List<CompositionWithAllParametersDTO> composition) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CompositionWithAllParametersDTO> getComposition() {
        return composition;
    }

    public void setComposition(List<CompositionWithAllParametersDTO> composition) {
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

}
