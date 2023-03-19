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
import by.it_academy.jd2.MJD29522.fitness.service.api.ISendingMailService;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalAccountService implements IPersonalAccountService {

    private final IPersonalAccountRepository personalAccountRepository;
    private final  IConversionToEntity conversionToEntity;
    private final ConversionService conversionService;
    private final ISendingMailService mailService;
    private final PasswordEncoder encoder;

    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public PersonalAccountService(IPersonalAccountRepository personalAccountRepository,
                                  IConversionToEntity conversionToEntity,
                                  ConversionService conversionService,
                                  ISendingMailService mailService,
                                  PasswordEncoder encoder
    ) {
        this.personalAccountRepository = personalAccountRepository;
        this.conversionToEntity = conversionToEntity;
        this.conversionService = conversionService;
        this.mailService = mailService;
        this.encoder = encoder;
    }

    @Override
    public boolean save(UserRegistrationDTO userRegistrationDTO) {
        if(userRegistrationDTO == null){
            throw new SingleErrorResponse("Введите данные");
        }
        validate(userRegistrationDTO);
        UserEntity entity = conversionToEntity.convertToEntity(userRegistrationDTO);
        String encode = encoder.encode(entity.getPassword());
        entity.setPassword(encode);
        personalAccountRepository.save(entity);
        String verificationCode = entity.getVerificationCode();
        String message = "Спасибо за регистрацию в нашем приложении!" +
                " Для успешной активации Вашего аккаунта введите Ваш почтовый адрес и код: " + entity.getVerificationCode();
        mailService.send(userRegistrationDTO.getMail(), "Активация в Fitness Studio", message);
        return true;
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        Optional<UserEntity> findUserEntity = personalAccountRepository.findById(uuid);
        if(findUserEntity.isEmpty()){
            throw new SingleErrorResponse("Пользователя с id " + uuid + " нет в базе данных!");
        }
        UserEntity userEntity = findUserEntity.get();
        return conversionService.convert(userEntity, UserDTO.class);
    }

//    @Override
//    public UserDTO aboutMe(String mail) {
//        UserEntity entityByMail = personalAccountRepository.findByMail(mail);
//        return conversionService.convert(entityByMail, UserDTO.class);
//    }

    @Override
    public void verify(String verificationCode, String mail) {
        if (verificationCode.isEmpty()){
            throw new SingleErrorResponse("Проверьте правильность верификационного кода");
        }
        UserEntity userEntity = personalAccountRepository.findByMail(mail);
        if(userEntity == null){
            throw new SingleErrorResponse("Такого пользователя не существует");
        }
        if (userEntity.getMail().equals(mail)
                && userEntity.getVerificationCode().equals(verificationCode)) {
            userEntity.setStatusEntity(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setVerificationCode(null);
            personalAccountRepository.save(userEntity);
          //  personalAccountRepository.deleteByVerificationCode(verificationCode);
        }  else throw new SingleErrorResponse("Проверьте правильность верификационного кода");
    }

    @Override
    public UserDTO login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = personalAccountRepository.findByMail(userLoginDTO.getMail());
        if(userEntity == null){
            throw new SingleErrorResponse("Такого пользователя не существует");
        }
        if ( !encoder.matches(userLoginDTO.getPassword(),userEntity.getPassword())){
            throw new SingleErrorResponse("Неверный пароль");
        }
        if( !userEntity.getStatusEntity().getStatus().equals(UserStatus.ACTIVATED)){
            throw new SingleErrorResponse("Ваш аккаунт должен быть активирован!");
        }
        // return JwtTokenUtil.generateAccessToken(userEntity);
        return conversionService.convert(userEntity, UserDTO.class );
    }

    @Override
    public void validate(UserRegistrationDTO userRegistrationDTO) {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

        Matcher matcher = EMAIL_PATTERN.matcher(userRegistrationDTO.getMail());
        if (userRegistrationDTO.getMail() == null || userRegistrationDTO.getMail().isBlank()) {
            multipleErrorResponse.setErrors(new Error("MAIL", "Поле не заполнено"));
        }
        if( !matcher.matches()){
            multipleErrorResponse.setErrors(new Error("MAIL","Введите корректный EMAIL"));
        }
        if (userRegistrationDTO.getFio() == null || userRegistrationDTO.getFio().isBlank()){
            multipleErrorResponse.setErrors(new Error("FIO", "Поле не заполнено"));
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
