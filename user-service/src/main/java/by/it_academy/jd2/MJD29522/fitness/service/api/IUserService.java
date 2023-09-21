package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {

    void addNewUser(@NotNull @Valid UserCreateDTO userCreateDTO) ;

    UserDTO getCard(UUID uuid);

    void update(@NotNull UUID uuid, @NotNull LocalDateTime dtUpdate, @NotNull @Valid UserCreateDTO userCreateDTO);

    UserDTO getUserByMail(String mail);

    PageDTO<UserDTO> getPage(int numberOfPage, int size);


}
