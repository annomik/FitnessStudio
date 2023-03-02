package by.it_academy.jd2.MJD29522.fitness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

//@Embeddable
@Entity
@Table(schema = "fitness", name = "composition")
public class CompositionEntity {

    @Id
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column(name = "weight")
    private int weight;

    @Column(name = "calories")
    private int calories;

    @Column(name = "proteins")
    private double proteins;

    @Column(name = "fats")
    private double fats;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipeEntity;


    public CompositionEntity() {
    }

    public CompositionEntity(UUID uuid, ProductEntity productEntity, int weight,
                             int calories, double proteins, double fats,
                             double carbohydrates, RecipeEntity recipeEntity) {
        this.uuid = uuid;
        this.productEntity = productEntity;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.recipeEntity = recipeEntity;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }
}
