package by.it_academy.jd2.MJD29522.fitness.service.api;


import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import java.util.UUID;

public interface IPersonalAccountService {

    boolean save(UserRegistrationDTO userRegistrationDTO);

    UserDTO getCard(UUID id);

    boolean verify(int verificationCode, String mail);

    UserLoginDTO login(UserLoginDTO userLoginDTO);

    boolean validate(UserRegistrationDTO userRegistrationDTO);


}
