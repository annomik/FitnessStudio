package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    void addNewProduct(@NotNull @Valid ProductCreateDTO productCreateDTO);

    Optional<ProductEntity> findByUUID(UUID uuid);

    void update(@NotNull UUID uuid,  @NotNull LocalDateTime dtUpdate, @NotNull @Valid ProductCreateDTO productCreateDTO);

    PageDTO<ProductDTO> getPage(int numberOfPage, int size);

}
