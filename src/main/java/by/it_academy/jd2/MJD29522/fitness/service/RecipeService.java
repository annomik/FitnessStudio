package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.CompositionDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.CompositionWithAllParametersDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.entity.CompositionEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.RecipeEntity;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IRecipeRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IProductService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IRecipeService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

public class RecipeService implements IRecipeService {

    private final IRecipeRepository recipeRepository;
    private final IProductService productService;
    private final ConversionService conversionService;

    public RecipeService(IRecipeRepository recipeRepository,
                         IProductService productService,
                         ConversionService conversionService) {
        this.recipeRepository = recipeRepository;
        this.productService = productService;
        this.conversionService = conversionService;
    }

    @Override
    public void addNewRecipe(RecipeCreateDTO recipeCreateDTO) {
        validate(recipeCreateDTO);

        LocalDateTime dtCreate = LocalDateTime.now(); //.withNano(3);
        List<CompositionDTO> compositionList = recipeCreateDTO.getComposition();
        List<CompositionEntity> compositionEntityList = new ArrayList<>();
        for(CompositionDTO composition : compositionList){
            Optional<ProductEntity> productFoundInDB =
                    productService.findByUUID(composition.getProduct().getUuid());
            ProductEntity productEntity = productFoundInDB.get();
            compositionEntityList.add(new CompositionEntity(
                   UUID.randomUUID(),
                    productEntity,
                   composition.getWeight()));
        }
        RecipeEntity recipeEntity = new RecipeEntity(UUID.randomUUID(),
                dtCreate,
                dtCreate,
                recipeCreateDTO.getTitle(),
                compositionEntityList
        );
        recipeRepository.save(recipeEntity);
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDTO recipeCreateDTO) {
        if (uuid == null || dtUpdate == null || recipeCreateDTO == null) {
            throw new SingleErrorResponse("?????????????? ?????????????????? ?????? ????????????????????");
        }
        validate(recipeCreateDTO);
        Optional<RecipeEntity> findEntity = recipeRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("?????????????? ?? id " + uuid + " ?????? ???????????????????? ???? ??????????????!");
        }
        RecipeEntity recipeEntity = findEntity.get();
        if (!(recipeEntity.getDtUpdate().isEqual(dtUpdate) && recipeEntity.getUuid().equals(uuid))) {
            throw new SingleErrorResponse("???????????? ?????????????? ?? id " + uuid + " ???? ??????????????????!");
        }
        List<CompositionDTO> compositionDTOList = recipeCreateDTO.getComposition();
        List<CompositionEntity> compositionEntityList = new ArrayList<>();
        for (CompositionDTO composition : compositionDTOList) {
            Optional<ProductEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
            ProductEntity productEntity = productFoundInDB.get();
            compositionEntityList.add(new CompositionEntity(
                    UUID.randomUUID(),
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
            List<CompositionEntity> compositionEntityList = recipeEntity.getComposition(); // ?????? ???????????? ????????????????????????(??????????????+??????
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

    @Override
    public void validate(RecipeCreateDTO recipeCreateDTO)  {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

        if (recipeCreateDTO.getTitle() == null || recipeCreateDTO.getTitle().isBlank() ) {
            multipleErrorResponse.setErrors(new Error("Title", "???????? ???? ??????????????????"));
        }
        RecipeEntity recipeByTitle = recipeRepository.findByTitle(recipeCreateDTO.getTitle());
        if (recipeByTitle != null) {
            multipleErrorResponse.setErrors(new Error("Title", "???????????? ?? ?????????? ?????????????????? ?????? ????????????????????"));
        }
        if (recipeCreateDTO.getComposition().size() == 0 ) {
            multipleErrorResponse.setErrors(new Error("Composition", "???????????? ???????????? ???????????????? ???????? ???? ???? 1 ????????????????"));
        }
        List<CompositionDTO> compositionDTOList = recipeCreateDTO.getComposition();
        for(CompositionDTO composition : compositionDTOList) {
            if (composition.getWeight() <= 0 && composition.getWeight() % 1 == 0) {
                multipleErrorResponse.setErrors(new Error("Weight", "?????????????? ?????????? ?????????????????????????? ??????????"));
            }
            Optional<ProductEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
            if (productFoundInDB.isEmpty()){
                multipleErrorResponse.setErrors(new Error(
                        "Product", "???????????????? ?? id " + composition.getProduct().getUuid() + " ?????? ?? ???????? ??????????????????"));
            }
        }

        if ( !multipleErrorResponse.getErrors().isEmpty()) {
            throw multipleErrorResponse;
        }
    }
}
