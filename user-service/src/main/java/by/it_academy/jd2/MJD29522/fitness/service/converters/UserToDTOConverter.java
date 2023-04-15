package by.it_academy.jd2.MJD29522.fitness.service.converters;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDTOConverter implements Converter<UserEntity, UserDTO> {

    @Override
    public UserDTO convert(UserEntity userEntity) {

        return new UserDTO(userEntity.getUuid(),
                userEntity.getDtCreate(), //.truncatedTo(ChronoUnit.MILLIS),
                userEntity.getDtUpdate(), //truncatedTo(ChronoUnit.MICROS),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRoleEntity().getRole(),
                userEntity.getStatusEntity().getStatus()
        );
    }
}
