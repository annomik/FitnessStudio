package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.repositories.IProductRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.*;
import by.it_academy.jd2.MJD29522.fitness.service.converters.ProductToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.converters.ProductToEntity;
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

public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductToEntity productToEntity;
    private final ProductToDTO productToDTO;

    public ProductService(IProductRepository productRepository,
                          ProductToEntity productToEntity,
                          ProductToDTO productToDTO) {

        this.productRepository = productRepository;
        this.productToEntity = productToEntity;
        this.productToDTO = productToDTO;
    }

    @Override
    public void addNewProduct(ProductCreateDTO productCreateDTO) {
        //validation!!!
        ProductEntity entity = productToEntity.convert(productCreateDTO);
        productRepository.save(entity);
    }

//    @Override
//    public void update(UUID uuid, long dtUpdate, UserCreateDTO userCreateDTO) {
//        Optional<UserEntity> findUserEntity = userRepository.findById(uuid);
//        UserEntity entity = findUserEntity.get();
//        if (entity != null) {
//            long epochMilli = ZonedDateTime.of(entity.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//
//            if ( epochMilli == dtUpdate && entity.getUuid().equals(uuid) ) {
//                entity.setDtUpdate(LocalDateTime.now());
//                entity.setMail(userCreateDTO.getMail());
//                entity.setFio(userCreateDTO.getFio());
//                entity.setRoleEntity(new RoleEntity(userCreateDTO.getRole()));
//                entity.setStatusEntity(new StatusEntity(userCreateDTO.getStatus()));
//                entity.setPassword(userCreateDTO.getPassword());
//                userRepository.save(entity);
//            }else{
//                throw new IllegalArgumentException("Версии пользователя с id " + uuid +" не совпадают!");
//            }
//        } else {
//            throw new IllegalArgumentException("Пользователя с id " + uuid + " для обновления не найдено!");
//        }
//    }

    @Override
    public void update(UUID uuid, long dtUpdate, ProductCreateDTO productCreateDTO) {
       Optional<ProductEntity> findEntity = productRepository.findById(uuid);
        ProductEntity entity = findEntity.get();
        if (entity != null) {
            long epochMilli = ZonedDateTime.of(entity.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli();

            if ( epochMilli == dtUpdate && entity.getUuid().equals(uuid) ) {
                entity.setDtUpdate(LocalDateTime.now());
                entity.setTitle(productCreateDTO.getTitle());
                entity.setWeight(productCreateDTO.getWeight());
                entity.setCalories(productCreateDTO.getCalories());
                entity.setProteins(productCreateDTO.getProteins());
                entity.setFats(productCreateDTO.getFats());
                entity.setCarbohydrates(productCreateDTO.getCarbohydrates());

                productRepository.save(entity);
            }else{
                throw new IllegalArgumentException("Версии продукта с id " + uuid +" не совпадают!");
            }
        } else {
            throw new IllegalArgumentException("Продукта с id " + uuid + " для обновления не найдено!");
        }

    }

    @Override
    public PageDTO<ProductDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);

        Page<ProductEntity> allEntity = productRepository.findAll(pageable);
        List<ProductDTO> content = new ArrayList<>();
        for (ProductEntity entity: allEntity) {
            ProductDTO productDTO = productToDTO.convert(entity);
            content.add(productDTO);
        }

        return new PageDTO<ProductDTO>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content  );
    }
}
