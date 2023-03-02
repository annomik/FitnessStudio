package by.it_academy.jd2.MJD29522.fitness.core.dto.food;


public class RecipeCreateDTO {

    private String title;
    private CompositionDTO composition;

    public RecipeCreateDTO() {
    }

    public RecipeCreateDTO(String title, CompositionDTO composition) {
        this.title = title;
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CompositionDTO getComposition() {
        return composition;
    }

    public void setComposition(CompositionDTO composition) {
        this.composition = composition;
    }
}
