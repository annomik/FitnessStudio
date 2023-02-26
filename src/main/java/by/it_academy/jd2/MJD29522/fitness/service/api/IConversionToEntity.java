package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;

public interface IConversionToEntity {

    UserEntity convertToEntity(UserRegistrationDTO userRegistrationDTO);
    UserEntity convertToEntity(UserCreateDTO userCreateDTO);
}
