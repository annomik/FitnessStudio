package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;

public class CompositionDTO {

    private ProductEntity productEntity;
    private int weight;

    public CompositionDTO() {
    }

    public CompositionDTO(ProductEntity productEntity, int weight) {
        this.productEntity = productEntity;
        this.weight = weight;
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
}
