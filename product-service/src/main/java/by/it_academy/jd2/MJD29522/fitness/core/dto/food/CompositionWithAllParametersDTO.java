package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionWithAllParametersDTO {

    private ProductDTO product;
    private int weight;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;

}
