package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.service.UserService;
import by.it_academy.jd2.MJD29522.fitness.service.PersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IPersonalAccountService personalAccountService(IPersonalAccountRepository repository){
        return new PersonalAccountService(repository);
    }

    @Bean
    public IUserService userService(IUserRepository userRepository, IPersonalAccountService userService){
        return new UserService(userRepository, userService);
    }
}
