package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IRecipeService {

    void addNewRecipe(@NotNull @Valid RecipeCreateDTO recipeCreateDTO);

    void update(@NotNull UUID uuid, @NotNull LocalDateTime dtUpdate, @NotNull RecipeCreateDTO recipeCreateDTO);

    PageDTO<RecipeDTO> getPage(int numberOfPage, int size);
}
