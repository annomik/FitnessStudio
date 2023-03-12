package by.it_academy.jd2.MJD29522.fitness.service.converters;

import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ProductToDTO implements Converter<ProductEntity, ProductDTO> {

    @Override
    public ProductDTO convert(ProductEntity productEntity) {

        return new ProductDTO(productEntity.getUuid(),
                productEntity.getDtCreate().truncatedTo(ChronoUnit.MILLIS),
                productEntity.getDtUpdate().truncatedTo(ChronoUnit.SECONDS),
                productEntity.getTitle(),
                productEntity.getWeight(),
                productEntity.getCalories(),
                productEntity.getProteins(),
                productEntity.getFats(),
                productEntity.getCarbohydrates()
        );

    }

}
