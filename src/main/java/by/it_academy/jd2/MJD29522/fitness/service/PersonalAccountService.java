package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;

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
        validate(userRegistrationDTO);
        UserEntity entity = conversionToEntity.convertToEntity(userRegistrationDTO);
        personalAccountRepository.save(entity);
        return true;
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        Optional<UserEntity> findUserEntity = personalAccountRepository.findById(uuid);
        if(findUserEntity.isEmpty()){
            throw new SingleErrorResponse("Пользователя с id " + uuid + " нет базе данных!");
        }
        UserEntity userEntity = findUserEntity.get();
        return conversionToDTO.convertToDTO(userEntity);
    }

    @Override
    public void verify(String verificationCode, String mail) {
        UserEntity userEntity = personalAccountRepository.findByMail(mail);
        if(userEntity == null){
            throw new SingleErrorResponse("Такого пользователя не существует");
        }
        if (userEntity.getMail().equals(mail)
                && userEntity.getVerificationCode().equals(verificationCode)) {
            userEntity.setStatusEntity(new StatusEntity(UserStatus.ACTIVATED));
            personalAccountRepository.save(userEntity);
        }  else throw new SingleErrorResponse("Проверьте правильность верификационного кода");
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = personalAccountRepository.findByMail(userLoginDTO.getMail());
        if(userEntity == null){
            throw new SingleErrorResponse("Такого пользователя не существует");
        }
        if (userEntity.getMail().equals(userLoginDTO.getMail()) && userEntity.getPassword().equals(userLoginDTO.getPassword())){
          //  System.out.println(" Вход выполнен");
        } else {
            throw new SingleErrorResponse("Неправильно введены данные");
        }
    }

    @Override
    public void validate(UserRegistrationDTO userRegistrationDTO) {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

        if (userRegistrationDTO.getFio() == null || userRegistrationDTO.getFio().isBlank()){
            multipleErrorResponse.setErrors(new Error("FIO", "Поле не заполнено"));
        }
        if (userRegistrationDTO.getMail() == null || userRegistrationDTO.getMail().isBlank()) {
            multipleErrorResponse.setErrors(new Error("MAIL", "Поле не заполнено"));
        }
        UserEntity userEntity = personalAccountRepository.findByMail(userRegistrationDTO.getMail());
        if(!(userEntity == null)){
            multipleErrorResponse.setErrors(new Error("MAIL","Пользователь с таким MAIL уже существует"));
        }
        if (userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isBlank()) {
            multipleErrorResponse.setErrors(new Error("Password", "Поле не заполнено"));
        }
        if ( !multipleErrorResponse.getErrors().isEmpty()) {
            throw multipleErrorResponse;
        }
    }
}
