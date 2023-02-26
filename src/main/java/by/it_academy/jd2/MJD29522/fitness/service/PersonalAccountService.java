package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.service.api.IConversionToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.core.convert.converter.Converter;
import java.util.Optional;
import java.util.UUID;

public class PersonalAccountService implements IPersonalAccountService {

    private final IPersonalAccountRepository personalAccountRepository;
    private final  IConversionToEntity conversionToEntity;
    private final IConversionToDTO conversionToDTO;

    public PersonalAccountService(IPersonalAccountRepository personalAccountRepository,
                                  IConversionToEntity conversionToEntity,
                                  IConversionToDTO conversionToDTO) {
        this.personalAccountRepository = personalAccountRepository;
        this.conversionToEntity = conversionToEntity;
        this.conversionToDTO = conversionToDTO;
    }

    @Override
    public boolean save(UserRegistrationDTO userRegistrationDTO) {
        UserEntity entity = conversionToEntity.convertToEntity(userRegistrationDTO);
        personalAccountRepository.save(entity);
        return true;
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        Optional<UserEntity> findUserEntity = personalAccountRepository.findById(uuid);
        UserEntity userEntity = findUserEntity.get();
        return conversionToDTO.convertToDTO(userEntity);
    }

    @Override
    public boolean verify(int verificationCode, String mail) {
        UserEntity userEntity = personalAccountRepository.findByMail(mail);
        if (userEntity.getMail().equals(mail)
                && userEntity.getVerificationCode() == verificationCode) {
            userEntity.setStatusEntity(new StatusEntity(UserStatus.ACTIVATED));
            System.out.println(" User verify!!!!! ");
            return true;
        }  System.out.println(" User  DON'T verify!!!!! ");
        return false;
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
