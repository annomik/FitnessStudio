package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface IPersonalAccountService {

    List<UserEntity> gelAll();

    boolean save(UserRegistrationDTO userRegistrationDTO);

    UserCreateDTO getCard(UUID uuid);

    boolean verify(String verificationCode, String mail);

    UserLoginDTO login(UserLoginDTO userLoginDTO);

    boolean validate(UserRegistrationDTO userRegistrationDTO);


}
