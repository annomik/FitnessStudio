package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.InputSingleDataException;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.ErrorCode;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IPersonalAccountRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.service.api.ISendingMailService;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Validated
@Transactional(readOnly = true)
public class PersonalAccountService implements IPersonalAccountService {

    private final IPersonalAccountRepository personalAccountRepository;
    private final  IConversionToEntity conversionToEntity;
    private final ConversionService conversionService;
    private final ISendingMailService mailService;
    private final PasswordEncoder encoder;
    private final String SUBJECT = "Activation in Fitness Studio";
    private final String MESSAGE = "Thank you for the registration in our application! \n " +
            " To successfully activate your account, please, enter your email address and this code: ";

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

    @Transactional
    @Override
    public boolean save(@NotNull @Valid UserRegistrationDTO userRegistrationDTO) {
        UserEntity entity = conversionToEntity.convertToEntity(userRegistrationDTO);
        String encode = encoder.encode(entity.getPassword());
        entity.setPassword(encode);
        personalAccountRepository.save(entity);
        String verificationCode = entity.getVerificationCode();
        String message = MESSAGE + entity.getVerificationCode();
        mailService.send(userRegistrationDTO.getMail(), SUBJECT, message);
        return true;
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        Optional<UserEntity> findUserEntity = personalAccountRepository.findById(uuid);
        if(findUserEntity.isEmpty()){
            throw new InputSingleDataException("User with id " + uuid + " not found.", ErrorCode.ERROR);
        }
        UserEntity userEntity = findUserEntity.get();
        return conversionService.convert(userEntity, UserDTO.class);
    }

    @Transactional
    @Override
    public void verify(String verificationCode, String mail) {
        if (verificationCode.isEmpty()){
            throw new InputSingleDataException("Please, check your verification code.", ErrorCode.ERROR);
        }
        UserEntity userEntity = personalAccountRepository.findByMail(mail);
        if(userEntity == null){
            throw new InputSingleDataException("The user does not exist. Please, get registered.", ErrorCode.ERROR);
        }
        if (userEntity.getMail().equals(mail)
                && userEntity.getVerificationCode().equals(verificationCode)) {
            userEntity.setStatusEntity(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setVerificationCode(null);
            personalAccountRepository.save(userEntity);
          //  personalAccountRepository.deleteByVerificationCode(verificationCode);
        }  else throw new InputSingleDataException("Please, check your verification code.", ErrorCode.ERROR);
    }

    @Override
    public UserDTO login(@NotNull @Valid UserLoginDTO userLoginDTO) {
        UserEntity userEntity = personalAccountRepository.findByMail(userLoginDTO.getMail());
        if(userEntity == null){
            throw new InputSingleDataException("The user does not exist. Please, get registered.", ErrorCode.ERROR);
        }
        if ( !encoder.matches(userLoginDTO.getPassword(),userEntity.getPassword())){
            throw new InputSingleDataException("Wrong password", ErrorCode.ERROR);
        }
        if( !userEntity.getStatusEntity().getStatus().equals(UserStatus.ACTIVATED)){
            throw new InputSingleDataException("Your account must be activated!", ErrorCode.ERROR);
        }
        // return JwtTokenUtil.generateAccessToken(userEntity);
        return conversionService.convert(userEntity, UserDTO.class );
    }

    public void validate(UserRegistrationDTO userRegistrationDTO) {
//        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();
//        if (userRegistrationDTO.getMail() == null || userRegistrationDTO.getMail().isBlank()) {
//            multipleErrorResponse.setErrors(new Error("MAIL", "The field is not filled"));
//        }
//        Matcher matcher = EMAIL_PATTERN.matcher(userRegistrationDTO.getMail());
//        if( !matcher.matches()){
//            multipleErrorResponse.setErrors(new Error("MAIL","Please, enter a valid EMAIL"));
//        }
//        if (userRegistrationDTO.getFio() == null || userRegistrationDTO.getFio().isBlank()){
//            multipleErrorResponse.setErrors(new Error("FIO", "The field is not filled"));
//        }
//        UserEntity userEntity = personalAccountRepository.findByMail(userRegistrationDTO.getMail());
//        if(!(userEntity == null)){
//            multipleErrorResponse.setErrors(new Error("MAIL","User with this email address already exists"));
//        }
//        if (userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isBlank()) {
//            multipleErrorResponse.setErrors(new Error("Password", "The field is not filled"));
//        }
//        if ( !multipleErrorResponse.getErrors().isEmpty()) {
//            throw multipleErrorResponse;
//        }
    }
}
