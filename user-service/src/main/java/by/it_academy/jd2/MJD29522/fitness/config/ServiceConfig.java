package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.repositories.api.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.service.UserService;
import by.it_academy.jd2.MJD29522.fitness.service.PersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.service.api.*;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IPersonalAccountService personalAccountService(IPersonalAccountRepository personalAccountRepository,
                                                          IConversionToEntity conversionToEntity,
                                                          ConversionService conversionService,
                                                          ISendingMailService mailService,
                                                          PasswordEncoder encoder
                                                          //JwtTokenUtil tokenUtil
    ){
        return new PersonalAccountService(personalAccountRepository, conversionToEntity,
                conversionService, mailService, encoder);
    }

    @Bean
    public IUserService userService(IUserRepository userRepository,
                                    IPersonalAccountService userService,
                                    ConversionService conversionService,
                                    IConversionToEntity conversionToEntity,
                                    PasswordEncoder encoder){
        return new UserService(userRepository, userService, conversionService,
                conversionToEntity, encoder);
    }
}
