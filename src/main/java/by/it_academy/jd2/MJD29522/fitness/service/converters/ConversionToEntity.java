package by.it_academy.jd2.MJD29522.fitness.service.converters;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.RoleEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ConversionToEntity implements IConversionToEntity
        //Converter<UserRegistrationDTO,UserEntity>
                                                        {

    @Override
    public UserEntity convertToEntity(UserRegistrationDTO userRegistrationDTO) {

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

    //admin created user
    @Override
    public UserEntity convertToEntity(UserCreateDTO userCreateDTO) {
        return new UserEntity(UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                userCreateDTO.getMail(),
                userCreateDTO.getFio(),
                new StatusEntity(userCreateDTO.getStatus()),
                userCreateDTO.getPassword(),
                new RoleEntity(userCreateDTO.getRole())
        );
    }
}
