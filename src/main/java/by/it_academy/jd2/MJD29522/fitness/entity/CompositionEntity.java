package by.it_academy.jd2.MJD29522.fitness.entity;

import jakarta.persistence.*;

@Embeddable
public class CompositionEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    private int weight;

    public CompositionEntity() {
    }

    public CompositionEntity(ProductEntity productEntity, int weight) {
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
