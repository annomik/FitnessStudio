package by.it_academy.jd2.MJD29522.fitness.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
//@Embeddable
@Table(schema = "fitness", name = "composition")
public class CompositionEntity {

    @Id
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_uuid")
    private ProductEntity productEntity;

    @Column(name = "weight")
    private int weight;

    public CompositionEntity() {
    }

    public CompositionEntity(UUID uuid, ProductEntity productEntity, int weight) {
        this.uuid = uuid;
        this.productEntity = productEntity;
        this.weight = weight;
    }

    public CompositionEntity(ProductEntity productEntity, int weight) {
        this.productEntity = productEntity;
        this.weight = weight;
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



}