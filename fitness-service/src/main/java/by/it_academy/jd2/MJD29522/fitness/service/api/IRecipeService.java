package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IRecipeService {

    void addNewRecipe(RecipeCreateDTO recipeCreateDTO);

    void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDTO recipeCreateDTO);

    PageDTO<RecipeDTO> getPage(int numberOfPage, int size);


    void validate(RecipeCreateDTO recipeCreateDTO);
}
