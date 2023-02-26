package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.RoleEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ConversionToEntity implements Converter<UserRegistrationDTO,UserEntity>{

    @Override
    public UserEntity convert(UserRegistrationDTO userRegistrationDTO) {

        return new UserEntity(UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                userRegistrationDTO.getMail(),
                userRegistrationDTO.getFio(),
                new StatusEntity(UserStatus.WAITING_ACTIVATION),
                userRegistrationDTO.getPassword(),
                (int)(Math.random() * 10000),
                new RoleEntity(UserRole.USER)
        );
    }

    public UserEntity convert(UserCreateDTO userCreateDTO) {

        return new UserEntity(UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                userCreateDTO.getMail(),
                userCreateDTO.getFio(),
                new StatusEntity(UserStatus.ACTIVATED),
                userCreateDTO.getPassword(),
                new RoleEntity(UserRole.USER)
        );
    }

}
