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
        LocalDateTime dtCreate = LocalDateTime.now().withNano(0);
        LocalDateTime dtUpdate = dtCreate;
        int verificationCode = (int)(Math.random() * 10000);
        return new UserEntity(UUID.randomUUID(),
                dtCreate,
                dtUpdate,
                userRegistrationDTO.getMail(),
                userRegistrationDTO.getFio(),
                new StatusEntity(UserStatus.WAITING_ACTIVATION),
                userRegistrationDTO.getPassword(),
                Integer.toString(verificationCode),
                new RoleEntity(UserRole.USER)
        );
    }

    //admin created user
    @Override
    public UserEntity convertToEntity(UserCreateDTO userCreateDTO) {
        LocalDateTime dtCreate = LocalDateTime.now().withNano(0);
        LocalDateTime dtUpdate = dtCreate;
        return new UserEntity(UUID.randomUUID(),
                dtCreate,
                dtUpdate,
                userCreateDTO.getMail(),
                userCreateDTO.getFio(),
                new StatusEntity(userCreateDTO.getStatus()),
                userCreateDTO.getPassword(),
                new RoleEntity(userCreateDTO.getRole())
        );
    }
}
