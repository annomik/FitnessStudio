package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import java.util.List;
import java.util.UUID;

public class PersonalAccountService implements IPersonalAccountService {

    private final IPersonalAccountRepository personalAccountRepository;

    public PersonalAccountService(IPersonalAccountRepository personalAccountRepository) {
        this.personalAccountRepository = personalAccountRepository;
    }

    public List<UserEntity> gelAll(){
        return personalAccountRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDTO userRegistrationDTO) {
        UserEntity entity = new UserEntity(userRegistrationDTO);
        personalAccountRepository.save(entity);
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
