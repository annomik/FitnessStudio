package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.Page;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;

import java.util.UUID;

public interface IAdminService {

    void addNewUser(UserCreateDTO userCreateDTO);

    UserCreateDTO getCard(UUID uuid);

    UserCreateDTO update(UUID uuid, long dtUpdate, UserCreateDTO userCreateDTO);

    Page getPage(int numberOfPage, int size);


}
