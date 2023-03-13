package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import by.it_academy.jd2.MJD29522.fitness.service.converters.serializers.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RecipeDTO {

    private UUID uuid;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtUpdate;

    private String title;

    private List<CompositionWithAllParametersDTO> composition;

    private int weight;
    private BigDecimal calories;
    private BigDecimal proteins;
    private BigDecimal fats;
    private BigDecimal carbohydrates;

    public RecipeDTO() {
    }

    public RecipeDTO(UUID uuid, LocalDateTime dtCreate,
                     LocalDateTime dtUpdate, String title,
                     List<CompositionWithAllParametersDTO> composition,
                     int weight, BigDecimal calories, BigDecimal proteins,
                     BigDecimal fats, BigDecimal carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
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
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getProteins() {
        return proteins;
    }

    public void setProteins(BigDecimal proteins) {
        this.proteins = proteins;
    }

    public BigDecimal getFats() {
        return fats;
    }

    public void setFats(BigDecimal fats) {
        this.fats = fats;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(BigDecimal carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
