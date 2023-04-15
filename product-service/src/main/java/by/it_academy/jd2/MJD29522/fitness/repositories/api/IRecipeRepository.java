package by.it_academy.jd2.MJD29522.fitness.repositories.api;

import by.it_academy.jd2.MJD29522.fitness.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface IRecipeRepository extends ListCrudRepository<RecipeEntity, UUID> {
    Page<RecipeEntity> findAll(Pageable pageable);
    RecipeEntity findByTitle(String title);
}
