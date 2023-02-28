package by.it_academy.jd2.MJD29522.fitness.service.converters;


import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ProductToDTO implements Converter<ProductEntity, ProductDTO> {

    @Override
    public ProductDTO convert(ProductEntity productEntity) {

        return new ProductDTO(productEntity.getUuid(),
                ZonedDateTime.of(productEntity.getDtCreate(), ZoneId.systemDefault()).toInstant().toEpochMilli(),
                ZonedDateTime.of(productEntity.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli(),
                productEntity.getTitle(),
                productEntity.getWeight(),
                productEntity.getCalories(),
                productEntity.getProteins(),
                productEntity.getFats(),
                productEntity.getCarbohydrates()
        );

    }

}
