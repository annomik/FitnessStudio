package by.it_academy.jd2.MJD29522.fitness.service.api;


import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import java.util.UUID;

public interface IPersonalAccountService {

    boolean save(UserRegistrationDTO userRegistrationDTO);

    UserDTO getCard(UUID id);

   // UserDTO aboutMe(String mail);

    void verify(String verificationCode, String mail);

    UserDTO login(UserLoginDTO userLoginDTO);

    void validate(UserRegistrationDTO userRegistrationDTO);


}
