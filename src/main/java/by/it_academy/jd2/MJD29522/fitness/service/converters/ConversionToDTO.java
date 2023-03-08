package by.it_academy.jd2.MJD29522.fitness.service.converters;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToDTO;
import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ConversionToDTO implements IConversionToDTO {

    @Override
    public UserDTO convertToDTO(UserEntity userEntity) {

        return new UserDTO(userEntity.getUuid(),
                userEntity.getDtCreate(),
                userEntity.getDtUpdate(),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRoleEntity().getRole(),
                userEntity.getStatusEntity().getStatus()
        );
    }


}
