package by.it_academy.jd2.MJD29522.fitness.service.converters;

import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
//import by.it_academy.jd2.MJD29522.fitness.core.dto.food.RecipeCreateDTO;
//import by.it_academy.jd2.MJD29522.fitness.entity.CompositionEntity;
//import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
//import by.it_academy.jd2.MJD29522.fitness.entity.RecipeEntity;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Component
//public class CompositionToEntity implements Converter<CompositionDTO, CompositionEntity> {
//
//    @Override
//    public CompositionEntity convert(CompositionDTO compositionDTO) {
//        ProductCreateDTO product = compositionDTO.getProduct();
//        int caloriesOfComposition =  compositionDTO.getWeight()* product.getCalories()/ product.getWeight();
//        double proteinsOfComposition =  compositionDTO.getWeight()* product.getProteins()/ product.getWeight();
//        double fatsOfComposition =  compositionDTO.getWeight()* product.getProteins()/ product.getWeight();
//        double carbohydratesOfComposition =  compositionDTO.getWeight()* product.getProteins()/ product.getWeight();
//
//        return new CompositionEntity(UUID.randomUUID(),
//                compositionDTO.getWeight(),
//                caloriesOfComposition,
//                proteinsOfComposition,
//                fatsOfComposition,
//                carbohydratesOfComposition);
//    }
//
//}
