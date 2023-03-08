package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import java.util.Objects;

public class CompositionDTO {

//    @JsonIgnore
//    private UUID uuid ;

    private ProductWithUUID product;
    private int weight;

    public CompositionDTO() {}

    public CompositionDTO(ProductWithUUID product, int weight) {
        this.product = product;
        this.weight = weight;
    }

    public ProductWithUUID getProduct() {
        return product;
    }

    public void setProduct(ProductWithUUID product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionDTO that = (CompositionDTO) o;
        return weight == that.weight && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight);
    }

    @Override
    public String toString() {
        return "CompositionDTO{" +
                "product=" + product +
                ", weight=" + weight +
                '}';
    }
}
