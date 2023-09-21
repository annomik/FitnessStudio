package by.it_academy.jd2.MJD29522.fitness.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(schema = "fitness", name = "composition")
public class CompositionEntity {

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private ProductEntity productEntity;

    @Column(name = "weight")
    private int weight;

}