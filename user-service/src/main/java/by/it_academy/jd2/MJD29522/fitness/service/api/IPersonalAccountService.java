package by.it_academy.jd2.MJD29522.fitness.service.api;


import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface IPersonalAccountService {

    boolean save(@NotNull @Valid UserRegistrationDTO userRegistrationDTO);

    UserDTO getCard(UUID id);

    void verify(String verificationCode, String mail);

    UserDTO login(@NotNull @Valid UserLoginDTO userLoginDTO);

}
