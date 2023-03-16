package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDTO getUser(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
