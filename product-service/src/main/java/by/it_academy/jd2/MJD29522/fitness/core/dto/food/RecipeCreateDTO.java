package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCreateDTO that = (RecipeCreateDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, composition);
    }

    @Override
    public String toString() {
        return "RecipeCreateDTO{" +
                "title='" + title + '\'' +
                ", composition=" + composition +
                '}';
    }
}
