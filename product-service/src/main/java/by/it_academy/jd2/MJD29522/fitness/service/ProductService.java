package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.errors.ErrorCode;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.InputSingleDataException;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IProductRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IProductService;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Validated
@Transactional(readOnly = true)
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public void addNewProduct(@NotNull @Valid ProductCreateDTO productCreateDTO) {
        if (productRepository.existsByTitle(productCreateDTO.getTitle())){
            throw new InputSingleDataException("The product with the same name already exists.", ErrorCode.ERROR);
        }
        ProductEntity entity = conversionService.convert(productCreateDTO, ProductEntity.class);
        productRepository.save(entity);
    }

    @Override
    public Optional<ProductEntity> findByUUID(UUID uuid){
        return  productRepository.findById(uuid);
    }

    @Transactional
    @Override
    public void update(@NotNull UUID uuid, @NotNull LocalDateTime dtUpdate, @NotNull @Valid  ProductCreateDTO productCreateDTO) {
        Optional<ProductEntity> findEntity = productRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new InputSingleDataException("The product with id " + uuid + " for update not found.", ErrorCode.ERROR);
        } else {
            ProductEntity entity = findEntity.get();
            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid)) {
                entity.setTitle(productCreateDTO.getTitle());
                entity.setWeight(productCreateDTO.getWeight());
                entity.setCalories(productCreateDTO.getCalories());
                entity.setProteins(productCreateDTO.getProteins());
                entity.setFats(productCreateDTO.getFats());
                entity.setCarbohydrates(productCreateDTO.getCarbohydrates());

                productRepository.save(entity);
            } else {
                throw new InputSingleDataException("Versions of the product with id " + uuid + " do not match.", ErrorCode.ERROR);
            }
        }
    }

    @Override
    public PageDTO<ProductDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<ProductEntity> allEntity = productRepository.findAll(pageable);
        List<ProductDTO> content = new ArrayList<>();
        for (ProductEntity entity: allEntity) {
            ProductDTO productDTO = conversionService.convert(entity, ProductDTO.class);
            content.add(productDTO);
        }
        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content );
    }

    public void validate(ProductCreateDTO productCreateDTO)  {
//
//        if (productCreateDTO.getTitle() == null || productCreateDTO.getTitle().isBlank()){
//            multipleErrorResponse.setErrors(new Error("Title", "The field is not filled."));
//        }
//        if (productCreateDTO.getWeight() <= 0 && productCreateDTO.getWeight() % 1 == 0){
//            multipleErrorResponse.setErrors(new Error("Weight", "The field 'Weight' must be an integer positive number."));
//        }
//        if (productCreateDTO.getCalories() <= 0 && productCreateDTO.getCalories() % 1 == 0) {
//            multipleErrorResponse.setErrors(new Error("Calories", "Enter an integer positive number."));
//        }
//        if (productCreateDTO.getProteins() < 0 ) {
//            multipleErrorResponse.setErrors(new Error("Proteins", "Please, enter a valid value. For example: 4.2"));
//        }
//        if (productCreateDTO.getFats() < 0 ) {
//            multipleErrorResponse.setErrors(new Error("Fats", "Please, enter a valid value. For example: 4.2"));
//        }
//        if (productCreateDTO.getCarbohydrates() < 0 ) {
//            multipleErrorResponse.setErrors(new Error("Carbohydrates", "Please, enter a valid value. For example: 50.2"));
//        }
//        if (!multipleErrorResponse.getErrors().isEmpty()) {
//            throw multipleErrorResponse;
//        }
    }
}
