package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonalAccountService implements IPersonalAccountService {

    private final IPersonalAccountRepository personalAccountRepository;
    private final Converter ConversionToEntity;
    private final ConversionToDTO conversionToDTO;

    public PersonalAccountService(IPersonalAccountRepository personalAccountRepository, Converter ConversionToEntity,
                                  ConversionToDTO conversionToDTO) {
        this.personalAccountRepository = personalAccountRepository;
        this.ConversionToEntity = ConversionToEntity;
        this.conversionToDTO = conversionToDTO;
    }

    public List<UserEntity> gelAll(){
        return personalAccountRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDTO userRegistrationDTO) {
       UserEntity entity = (UserEntity) ConversionToEntity.convert(userRegistrationDTO);
        personalAccountRepository.save(entity);
        return true;
    }

    @Override
    public UserDTO getCard(UUID uuid) {
       // UUID uuid = UUID.fromString(id);
        Optional<UserEntity> findUserEntity = personalAccountRepository.findById(uuid);
        UserEntity userEntity = findUserEntity.get();
        return conversionToDTO.convertToDTO(userEntity);
    }

    @Override
    public boolean verify(String verificationCode, String mail) {
        return true;
    }

    @Override
    public UserLoginDTO login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = personalAccountRepository.findByMail(userLoginDTO.getMail());
        if (userEntity.getMail().equals(userLoginDTO.getMail()) && userEntity.getPassword().equals(userLoginDTO.getPassword())){
            System.out.println(" Вход выполнен");
        } else {
            throw new IllegalArgumentException("Неправильно введены данные");
        }
        return null;
    }

    @Override
    public boolean validate(UserRegistrationDTO userRegistrationDTO) {
       return true;

    }
}
