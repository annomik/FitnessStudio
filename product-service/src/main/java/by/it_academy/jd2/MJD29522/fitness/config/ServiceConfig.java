package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.repositories.api.IProductRepository;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IRecipeRepository;
import by.it_academy.jd2.MJD29522.fitness.service.ProductService;
import by.it_academy.jd2.MJD29522.fitness.service.RecipeService;
import by.it_academy.jd2.MJD29522.fitness.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class ServiceConfig {

    @Bean
    public IProductService productService(IProductRepository productRepository,
                                          ConversionService conversionService
                                         ){
        return new ProductService(productRepository, conversionService);
    }

    @Bean
    public IRecipeService recipeService(IRecipeRepository recipeRepository,
                                        IProductService productService,
                                        ConversionService conversionService){
        return new RecipeService(recipeRepository, productService, conversionService);
    }

}
