package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import java.util.Objects;

public class CompositionWithAllParametersDTO {

    private ProductDTO product;
    private int weight;
    private double calories;   //!!!
    private double proteins;
    private double fats;
    private double carbohydrates;

    public CompositionWithAllParametersDTO() {
    }

    public CompositionWithAllParametersDTO(ProductDTO product, int weight,
                                           double calories, double proteins,
                                           double fats, double carbohydrates) {
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionWithAllParametersDTO that = (CompositionWithAllParametersDTO) o;
        return weight == that.weight && Double.compare(that.calories, calories) == 0 && Double.compare(that.proteins, proteins) == 0 && Double.compare(that.fats, fats) == 0 && Double.compare(that.carbohydrates, carbohydrates) == 0 && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "CompositionWithAllParametersDTO{" +
                "product=" + product +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
