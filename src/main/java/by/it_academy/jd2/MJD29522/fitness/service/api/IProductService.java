package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    void addNewProduct(ProductCreateDTO productCreateDTO);

    Optional<ProductEntity> findByUUID(UUID uuid);

    void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDTO productCreateDTO);

    PageDTO<ProductDTO> getPage(int numberOfPage, int size);


}
