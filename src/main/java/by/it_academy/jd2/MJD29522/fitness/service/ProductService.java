package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IProductRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ConversionService conversionService;
   // private final ProductToEntity productToEntity;
   // private final ProductToDTO productToDTO;

    public ProductService(IProductRepository productRepository, ConversionService conversionService) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void addNewProduct(ProductCreateDTO productCreateDTO) {
        validate(productCreateDTO);
        ProductEntity entity = conversionService.convert(productCreateDTO, ProductEntity.class);
        productRepository.save(entity);
    }

    @Override
    public Optional<ProductEntity> findByUUID(UUID uuid){
        return  productRepository.findById(uuid);
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDTO productCreateDTO) {
        if(uuid == null || dtUpdate == null){
            throw new SingleErrorResponse("Введите параметры для обновления");
        }
        validate(productCreateDTO);
        Optional<ProductEntity> findEntity = productRepository.findById(uuid);

        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("Продукта с id " + uuid + " для обновления не найдено!");
        } else {
            ProductEntity entity = findEntity.get();
            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid)) {
                entity.setDtUpdate(LocalDateTime.now().withNano(3));
                entity.setTitle(productCreateDTO.getTitle());
                entity.setWeight(productCreateDTO.getWeight());
                entity.setCalories(productCreateDTO.getCalories());
                entity.setProteins(productCreateDTO.getProteins());
                entity.setFats(productCreateDTO.getFats());
                entity.setCarbohydrates(productCreateDTO.getCarbohydrates());

                productRepository.save(entity);
            } else {
                throw new SingleErrorResponse("Версии продукта с id " + uuid + " не совпадают!");
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

    @Override
    public void validate(ProductCreateDTO productCreateDTO)  {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

        if (productCreateDTO.getTitle() == null || productCreateDTO.getTitle().isBlank()){
            multipleErrorResponse.setErrors(new Error("Title", "Поле не заполнено"));
        }
        if (productCreateDTO.getWeight() <= 0 ){     //|| productCreateDTO.getWeight()) {
            multipleErrorResponse.setErrors(new Error("Weight", "Введите целое положительное число"));
        }
        if (productCreateDTO.getCalories() <= 0 ) {
            multipleErrorResponse.setErrors(new Error("Calories", "Введите целое положительное число"));
        }
        if (productCreateDTO.getProteins() <= 0 ) {
            multipleErrorResponse.setErrors(new Error("Proteins", "Введите корректное значение. Например: 4.2"));
        }
        if (productCreateDTO.getFats() <= 0 ) {
            multipleErrorResponse.setErrors(new Error("Fats", "Введите корректное значение. Например: 4.2"));
        }
        if (productCreateDTO.getCarbohydrates() <= 0 ) {
            multipleErrorResponse.setErrors(new Error("Carbohydrates", "Введите корректное значение. Например: 50.2"));
        }

        if (!multipleErrorResponse.getErrors().isEmpty()) {
            throw multipleErrorResponse;
        }

    }
}
