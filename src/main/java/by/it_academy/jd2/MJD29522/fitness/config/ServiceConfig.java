package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IAdminRepository;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.service.AdminService;
import by.it_academy.jd2.MJD29522.fitness.service.UserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IAdminService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IUserService userService(IUserRepository repository){
        return new UserService(repository);
    }

    @Bean
    public IAdminService adminService(IAdminRepository adminRepository){
        return new AdminService(adminRepository);
    }
}
