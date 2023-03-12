package by.it_academy.jd2.MJD29522.fitness.service.converters;

import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProductToEntity implements Converter<ProductCreateDTO, ProductEntity> {

    @Override
    public ProductEntity convert(ProductCreateDTO productCreateDTO) {
        LocalDateTime dtCreate = LocalDateTime.now().withNano(3);

        return new ProductEntity(UUID.randomUUID(),
                dtCreate,
                dtCreate,
                productCreateDTO.getTitle(),
                productCreateDTO.getWeight(),
                productCreateDTO.getCalories(),
                productCreateDTO.getProteins(),
                productCreateDTO.getFats(),
                productCreateDTO.getCarbohydrates());
    }

}
