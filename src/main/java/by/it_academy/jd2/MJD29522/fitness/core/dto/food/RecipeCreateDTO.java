package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import java.util.List;

public class RecipeCreateDTO {

    private String title;
    private List<CompositionDTO> composition;

    public RecipeCreateDTO() {
    }

    public RecipeCreateDTO(String title, List<CompositionDTO> composition) {
        this.title = title;
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CompositionDTO> getComposition() {
        return composition;
    }


}
