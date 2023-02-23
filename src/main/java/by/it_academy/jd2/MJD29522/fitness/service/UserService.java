package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IUserRepository;

import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;

import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {

    private final IUserRepository IUserRepository;

    public UserService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public List<UserEntity> gelAll(){
        return IUserRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDTO userRegistrationDTO) {
        UserEntity entity = new UserEntity(userRegistrationDTO);
       // MailEntity mailEntity = new MailEntity(userRegistrationDTO.getMail(), (int)  (Math.random() * 10000));
        IUserRepository.save(entity);
        return true;
    }

    @Override
    public UserCreateDTO getCard(UUID uuid) {
        return null;
    }

    @Override
    public boolean verify(String verificationCode, String mail) {
        return true;
    }

    @Override
    public UserLoginDTO login(UserLoginDTO userLoginDTO) {
        return null;
    }

    @Override
    public boolean validate(UserRegistrationDTO userRegistrationDTO) {
       return true;

    }
}
