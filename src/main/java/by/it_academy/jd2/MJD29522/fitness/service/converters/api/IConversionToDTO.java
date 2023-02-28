package by.it_academy.jd2.MJD29522.fitness.service.converters.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;

public interface IConversionToDTO {


    UserDTO convertToDTO(UserEntity userEntity);
}
