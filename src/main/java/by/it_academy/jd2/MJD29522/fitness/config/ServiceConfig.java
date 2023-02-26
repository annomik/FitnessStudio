package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.service.ConversionToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.UserService;
import by.it_academy.jd2.MJD29522.fitness.service.PersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ServiceConfig {

    @Bean
    public IPersonalAccountService personalAccountService(IPersonalAccountRepository personalAccountRepository,
                                                          Converter conversionToEntity,
                                                          ConversionToDTO conversionToDTO){
        return new PersonalAccountService(personalAccountRepository, conversionToEntity, conversionToDTO);
    }

    @Bean
    public IUserService userService(IUserRepository userRepository,
                                    IPersonalAccountService userService,
                                    ConversionToDTO conversionToDTO){
        return new UserService(userRepository, userService, conversionToDTO);
    }
}
