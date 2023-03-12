package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.CompositionDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.entity.CompositionEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.RecipeEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IRecipeRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IProductService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IRecipeService;
import by.it_academy.jd2.MJD29522.fitness.service.converters.ProductToDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
        //validation!

        LocalDateTime dtCreate = LocalDateTime.now(); //.withNano(3);

        List<CompositionDTO> compositionList = recipeCreateDTO.getComposition();
        List<CompositionEntity> compositionEntityList = new ArrayList<>();
        for(CompositionDTO composition : compositionList){
            Optional<ProductEntity> productFoundInDB =
                    productService.findByUUID(composition.getProduct().getUuid());
            ProductEntity productEntity = productFoundInDB.get(); //validation!
            compositionEntityList.add(new CompositionEntity(
                   UUID.randomUUID(),  // поиск Composition ???
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
            throw new SingleErrorResponse("Введите параметры для обновления");
        }
        Optional<RecipeEntity> findEntity = recipeRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("Рецепта с id " + uuid + " для обновления не найдено!");
        }
        RecipeEntity recipeEntity = findEntity.get();

        if (!(recipeEntity.getDtUpdate().isEqual(dtUpdate) && recipeEntity.getUuid().equals(uuid))) {
            throw new SingleErrorResponse("Версии рецепта с id " + uuid + " не совпадают!");
        }
        List<CompositionDTO> compositionList = recipeCreateDTO.getComposition();
        List<CompositionEntity> compositionEntityList = new ArrayList<>();
        for (CompositionDTO composition : compositionList) {
            Optional<ProductEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
            ProductEntity productEntity = productFoundInDB.get();
            //validation! not null
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
          List<RecipeDTO> content = new ArrayList<>();
//        for (RecipeEntity recipeEntity: allEntity) {
//            List<CompositionEntity> compositionEntityList = recipeEntity.getComposition(); // все энтити ингредиентов
//            for(CompositionEntity compositionEntity : compositionEntityList){
//                Optional<ProductEntity> productFoundInDB = productService.findByUUID(compositionEntity.getProductEntity().getUuid());
//                ProductEntity productEntity = productFoundInDB.get();
//                ProductDTO productDTO = productToDTO.convert(productEntity)
//                        )
//            }
//
//            RecipeDTO recipeDTO = productToDTO.convert(entity);
//            content.add(productDTO);
//        }

        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content  );

    }

    public boolean validate(RecipeCreateDTO recipeCreateDTO)  {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

//        if (userCreateDTO.getFio() == null || userCreateDTO.getFio().isBlank()){
//            multipleErrorResponse.setErrors(new Error("FIO", "Поле не заполнено"));
//        }
//        if (userCreateDTO.getMail() == null || userCreateDTO.getMail().isBlank()) {
//            multipleErrorResponse.setErrors(new Error("MAIL", "Поле не заполнено"));
//        }
//
//        if (userCreateDTO.getPassword() == null || userCreateDTO.getPassword().isBlank()) {
//            multipleErrorResponse.setErrors(new Error("Password", "Поле не заполнено"));
//        }
//
        if (multipleErrorResponse.getErrors().isEmpty()) {
            return true;
        }
        throw multipleErrorResponse;

    }
}
