package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.Page;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IAdminRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IAdminService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;

import java.util.UUID;

public class AdminService implements IAdminService {

    private final IAdminRepository IAdminRepository;
   // private final IUserService userService;

    public AdminService(IAdminRepository IAdminRepository) {
        this.IAdminRepository = IAdminRepository;

    }

    @Override
    public void addNewUser(UserCreateDTO userCreateDTO) {

    }

    @Override
    public UserCreateDTO getCard(UUID uuid) {

        return null;
                //userService.getCard(uuid);
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
