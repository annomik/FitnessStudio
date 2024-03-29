package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.errors.ErrorCode;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.*;
import by.it_academy.jd2.MJD29522.fitness.core.exception.InputSingleDataException;
import by.it_academy.jd2.MJD29522.fitness.entity.CompositionEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.RecipeEntity;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IRecipeRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IProductService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IRecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService implements IRecipeService {
    private final IRecipeRepository recipeRepository;
    private final IProductService productService;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public void addNewRecipe(@NotNull @Valid RecipeCreateDTO recipeCreateDTO) {
        if (recipeRepository.existsByTitle(recipeCreateDTO.getTitle()) ) {
            throw new InputSingleDataException("A recipe with the same name already exists.", ErrorCode.ERROR);
        }
        LocalDateTime dtCreate = LocalDateTime.now(); //.withNano(3);
        List<CompositionDTO> compositionList = recipeCreateDTO.getComposition();
        List<CompositionEntity> compositionEntityList = new ArrayList<>();
        for(CompositionDTO composition : compositionList){
            Optional<ProductEntity> productFoundInDB =
                    productService.findByUUID(composition.getProduct().getUuid());
            ProductEntity productEntity = productFoundInDB.get();
            compositionEntityList.add(new CompositionEntity(
                        productEntity,
                        composition.getWeight()));
        }
        RecipeEntity recipeEntity = new RecipeEntity(UUID.randomUUID(),
                dtCreate,
                dtCreate,
                recipeCreateDTO.getTitle(),
                compositionEntityList
        );
        System.out.println(recipeEntity);
        recipeRepository.save(recipeEntity);   //!
    }
    @Transactional
    @Override
    public void update(@NotNull UUID uuid,@NotNull LocalDateTime dtUpdate, @NotNull @Valid RecipeCreateDTO recipeCreateDTO) {
        Optional<RecipeEntity> findEntity = recipeRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new InputSingleDataException("A recipe with id " + uuid + " for update not found!", ErrorCode.ERROR);
        }
        RecipeEntity recipeEntity = findEntity.get();
        if (!(recipeEntity.getDtUpdate().isEqual(dtUpdate) && recipeEntity.getUuid().equals(uuid))) {
            throw new InputSingleDataException("Versions of the recipe with id " + uuid + " do not match!", ErrorCode.ERROR);
        }
        List<CompositionDTO> compositionDTOList = recipeCreateDTO.getComposition();
        List<CompositionEntity> compositionEntityList = new ArrayList<>();
        for (CompositionDTO composition : compositionDTOList) {
            Optional<ProductEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
            ProductEntity productEntity = productFoundInDB.get();
            compositionEntityList.add(new CompositionEntity(
                         productEntity,
                         composition.getWeight()));
        }
        recipeEntity.setTitle(recipeCreateDTO.getTitle());
        recipeEntity.setComposition(compositionEntityList);
        recipeRepository.save(recipeEntity);
    }

    @Override
    public PageDTO<RecipeDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<RecipeEntity> allEntity = recipeRepository.findAll(pageable);
        List<RecipeDTO> content = convertListRecipeEntityToDTO(allEntity);

        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content
        );
    }

    private List<RecipeDTO> convertListRecipeEntityToDTO(Page<RecipeEntity> allEntity){
        List<RecipeDTO> content = new ArrayList<>();
        for (RecipeEntity recipeEntity: allEntity) {
            int weightOfRecipe = 0;
            double caloriesOfRecipe = 0;
            double proteinsOfRecipe = 0;
            double fatsOfRecipe = 0;
            double carbohydratesOfRecipe = 0;
            List<CompositionEntity> compositionEntityList = recipeEntity.getComposition(); // все энтити ингредиентов(продукт+вес
            List<CompositionWithAllParametersDTO> composition = new ArrayList<>();
            for(CompositionEntity compositionEntity : compositionEntityList) {
                Optional<ProductEntity> productFoundInDB = productService.findByUUID(compositionEntity.getProductEntity().getUuid());
                ProductEntity productEntity = productFoundInDB.get(); //product from db
                ProductDTO productDTO = conversionService.convert(productEntity, ProductDTO.class);
                double calories =compositionEntity.getWeight() * productDTO.getCalories() / productDTO.getWeight();
                double proteins = compositionEntity.getWeight() * productDTO.getProteins() / productDTO.getWeight();
                double fats = compositionEntity.getWeight() * productDTO.getFats() / productDTO.getWeight();
                double carbohydrates = compositionEntity.getWeight() * productDTO.getCarbohydrates() / productDTO.getWeight();

                CompositionWithAllParametersDTO compositionWithAllParameters = new CompositionWithAllParametersDTO(
                        productDTO,
                        compositionEntity.getWeight(),
                        calories,
                        proteins,
                        fats,
                        carbohydrates);
                composition.add(compositionWithAllParameters);
                weightOfRecipe +=  compositionEntity.getWeight();
                caloriesOfRecipe += calories ;
                proteinsOfRecipe += proteins;
                fatsOfRecipe += fats ;
                carbohydratesOfRecipe += carbohydrates;
            }
            RecipeDTO recipeDTO = new RecipeDTO(recipeEntity.getUuid(),
                    recipeEntity.getDtCreate(),
                    recipeEntity.getDtUpdate(),
                    recipeEntity.getTitle(),
                    composition,
                    weightOfRecipe,
                    BigDecimal.valueOf(caloriesOfRecipe).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(proteinsOfRecipe).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(fatsOfRecipe).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(carbohydratesOfRecipe).setScale(2, RoundingMode.HALF_UP)
            );
            content.add(recipeDTO);
        }
        return content;
    }


    public void validate(RecipeCreateDTO recipeCreateDTO)  {
//        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();
//
//        if (recipeCreateDTO.getTitle() == null || recipeCreateDTO.getTitle().isBlank() ) {
//            multipleErrorResponse.setErrors(new Error("Title", "The field is not filled"));
//        }
//        if (recipeCreateDTO.getComposition().size() == 0 ) {
//            multipleErrorResponse.setErrors(new Error("Composition", "Recipe must contain at least 1 product"));
//        }
//        List<CompositionDTO> compositionDTOList = recipeCreateDTO.getComposition();
//        for(CompositionDTO composition : compositionDTOList) {
//            if (composition.getWeight() <= 0 && composition.getWeight() % 1 == 0) {
//                multipleErrorResponse.setErrors(new Error("Weight", "Enter a positive number"));
//            }
//            Optional<ProductEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
//            if (productFoundInDB.isEmpty()){
//                multipleErrorResponse.setErrors(new Error(
//                        "Product",
//                        "The product with id " + composition.getProduct().getUuid() + " was not found in the product database"));
//            }
//        }
//
//        if ( !multipleErrorResponse.getErrors().isEmpty()) {
//            throw multipleErrorResponse;
        }

}
