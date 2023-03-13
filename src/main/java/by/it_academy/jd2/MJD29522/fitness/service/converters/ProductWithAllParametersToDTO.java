//package by.it_academy.jd2.MJD29522.fitness.service.converters;
//
//import by.it_academy.jd2.MJD29522.fitness.core.dto.food.CompositionDTO;
//import by.it_academy.jd2.MJD29522.fitness.core.dto.food.CompositionWithAllParametersDTO;
//import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
//import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
//import by.it_academy.jd2.MJD29522.fitness.entity.CompositionEntity;
//import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class ProductWithAllParametersToDTO implements Converter<ProductEntity, ProductDTO> {
//
//
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
//    @Override
//    public ProductDTO convert(ProductEntity productEntity) {
//
//
//        return new ProductDTO();
//    }
//}
