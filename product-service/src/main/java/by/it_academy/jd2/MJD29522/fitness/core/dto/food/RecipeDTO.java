package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import by.it_academy.jd2.MJD29522.fitness.service.converters.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RecipeDTO {

    private UUID uuid;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtCreate;
  //  @JsonSerialize(using = LocalDateTimeToLongMillisSerializer.class)
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
    public BigDecimal getProteins() {
        return proteins;
    }
    public BigDecimal getFats() {
        return fats;
    }
    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipeDTO = (RecipeDTO) o;
        return weight == recipeDTO.weight && Objects.equals(uuid, recipeDTO.uuid) && Objects.equals(dtCreate, recipeDTO.dtCreate) && Objects.equals(dtUpdate, recipeDTO.dtUpdate) && Objects.equals(title, recipeDTO.title) && Objects.equals(composition, recipeDTO.composition) && Objects.equals(calories, recipeDTO.calories) && Objects.equals(proteins, recipeDTO.proteins) && Objects.equals(fats, recipeDTO.fats) && Objects.equals(carbohydrates, recipeDTO.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, composition, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", composition=" + composition +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
