package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserDTO;

import java.util.UUID;

public interface IUserService {

    void addNewUser(UserCreateDTO userCreateDTO);

    UserDTO getCard(UUID uuid);

    void update(UUID uuid, long dtUpdate, UserCreateDTO userCreateDTO);

    PageDTO<UserDTO> getPage(int numberOfPage, int size);


}
