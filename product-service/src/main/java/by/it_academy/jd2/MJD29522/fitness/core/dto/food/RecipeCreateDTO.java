package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCreateDTO {
    @ValidString
    private String title;
    private List<CompositionDTO> composition;

}
