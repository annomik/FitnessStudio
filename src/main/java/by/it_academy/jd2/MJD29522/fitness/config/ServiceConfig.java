package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.repositories.api.IProductRepository;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IRecipeRepository;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.service.ProductService;
import by.it_academy.jd2.MJD29522.fitness.service.RecipeService;
import by.it_academy.jd2.MJD29522.fitness.service.UserService;
import by.it_academy.jd2.MJD29522.fitness.service.PersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IProductService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IRecipeService;
import by.it_academy.jd2.MJD29522.fitness.service.converters.ProductToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.converters.ProductToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class ServiceConfig {

    @Bean
    public IPersonalAccountService personalAccountService(IPersonalAccountRepository personalAccountRepository,
                                                          IConversionToEntity conversionToEntity,
                                                          IConversionToDTO conversionToDTO){
        return new PersonalAccountService(personalAccountRepository, conversionToEntity, conversionToDTO);
    }

    @Bean
    public IUserService userService(IUserRepository userRepository,
                                    IPersonalAccountService userService,
                                    IConversionToDTO conversionToDTO,
                                    IConversionToEntity conversionToEntity){
        return new UserService(userRepository, userService, conversionToDTO, conversionToEntity);
    }

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
