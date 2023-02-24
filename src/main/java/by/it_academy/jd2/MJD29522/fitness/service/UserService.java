package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.Page;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;

import java.util.UUID;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IPersonalAccountService authenticationService;

    public UserService(IUserRepository userRepository, IPersonalAccountService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public void addNewUser(UserCreateDTO userCreateDTO) {

    }

    @Override
    public UserCreateDTO getCard(UUID uuid) {

        return authenticationService.getCard(uuid);
    }

    @Override
    public UserCreateDTO update(UUID uuid, long dtUpdate, UserCreateDTO userCreateDTO) {
        return null;
    }

    @Override
    public Page getPage(int numberOfPage, int size) {

        return null;
    }
}
