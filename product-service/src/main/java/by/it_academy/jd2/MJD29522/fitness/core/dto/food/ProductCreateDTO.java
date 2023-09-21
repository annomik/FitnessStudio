package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    @ValidString
    private String title;
    @Positive
    private int weight;
    @Positive
    private int calories;
    @PositiveOrZero(message = "Value must not be negative")
    private double proteins;
    @PositiveOrZero(message = "Value must not be negative")
    private double fats;
    @PositiveOrZero(message = "Value must not be negative")
    private double carbohydrates;

}
