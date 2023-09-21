package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionDTO {
    private ProductWithUUID product;
    @Positive(message = "Weight has to be positive number")
    private int weight;
}
