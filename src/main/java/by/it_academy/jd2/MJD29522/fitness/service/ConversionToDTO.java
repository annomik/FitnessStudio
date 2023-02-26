package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.RoleEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.service.api.IConversionToDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class ConversionToDTO implements IConversionToDTO {

    @Override
    public UserDTO convertToDTO(UserEntity userEntity) {
      //  UserDTO userDTO =
        return new UserDTO(userEntity.getUuid(),
                ZonedDateTime.of(userEntity.getDtCreate(), ZoneId.systemDefault()).toInstant().toEpochMilli(),
                ZonedDateTime.of(userEntity.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli(),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRoleEntity().getRole(),
                userEntity.getStatusEntity().getStatus()
        );
    }


}
