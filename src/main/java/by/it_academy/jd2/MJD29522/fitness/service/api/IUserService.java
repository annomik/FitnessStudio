package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {

    void addNewUser(UserCreateDTO userCreateDTO) ;

    UserDTO getCard(UUID uuid);

    void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDTO userCreateDTO);

    PageDTO<UserDTO> getPage(int numberOfPage, int size);


}
