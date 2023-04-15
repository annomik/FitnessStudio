package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.RoleEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IPersonalAccountService personalAccountService;
    private final ConversionService conversionService;
    private final IConversionToEntity conversionToEntity;
    private final PasswordEncoder encoder;

    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserService(IUserRepository userRepository,
                       IPersonalAccountService personalAccountService,
                       ConversionService conversionService,
                       IConversionToEntity conversionToEntity,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.personalAccountService = personalAccountService;
        this.conversionService = conversionService;
        this.conversionToEntity = conversionToEntity;
        this.encoder = encoder;
    }

    @Override
    public void addNewUser(UserCreateDTO userCreateDTO) {
        if(userCreateDTO == null){
            throw new SingleErrorResponse("Enter the data");
        }
        UserEntity userEntity = userRepository.findByMail(userCreateDTO.getMail());
        if(userEntity != null){
            throw new SingleErrorResponse(("User with this email address already exists"));
        }
        validate(userCreateDTO);
        UserEntity entity = conversionToEntity.convertToEntity(userCreateDTO);
        String encode = encoder.encode(entity.getPassword());
        entity.setPassword(encode);
        userRepository.save(entity);
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDTO userCreateDTO) {
        validate(userCreateDTO);
        if(uuid == null || userCreateDTO == null){
            throw new SingleErrorResponse("Enter parameters for update");
        }

        Optional<UserEntity> userEntityFromDB = userRepository.findById(uuid);
        if (userEntityFromDB.isEmpty()) {
            throw new SingleErrorResponse("User with id " + uuid + " for update not found.");
        } else {
            UserEntity entity = userEntityFromDB.get();
            if (!entity.getDtUpdate().isEqual(dtUpdate) ) {
                throw new SingleErrorResponse("Versions for the user with id " + uuid + " do not match");
            }else{
                String encode = encoder.encode(userCreateDTO.getPassword());

                entity.setMail(userCreateDTO.getMail());
                entity.setFio(userCreateDTO.getFio());
                entity.setRoleEntity(new RoleEntity(userCreateDTO.getRole()));
                entity.setStatusEntity(new StatusEntity(userCreateDTO.getStatus()));
                entity.setPassword(encode);
                userRepository.save(entity);
            }
        }
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        return personalAccountService.getCard(uuid);
    }

    @Override
    public UserDTO getUserByMail(String mail) {
        UserEntity userEntity = userRepository.findByMail(mail);
//        if(userEntity == null){
//            throw new SingleErrorResponse("Пользователя с mail " + mail + " нет базе данных!");
//        }
        return conversionService.convert(userEntity, UserDTO.class);
    }

    @Override
    public PageDTO<UserDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<UserEntity> allEntity = userRepository.findAll(pageable);
        List<UserDTO> content = new ArrayList<>();

        for (UserEntity entity: allEntity) {
            UserDTO userDTO = conversionService.convert(entity, UserDTO.class);
            content.add(userDTO);
        }
        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content);
    }

    public void validate(UserCreateDTO userCreateDTO)  {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

        Matcher matcher = EMAIL_PATTERN.matcher(userCreateDTO.getMail());
        if( !matcher.matches()){
            multipleErrorResponse.setErrors(new Error("MAIL","Please, enter a valid EMAIL"));
        }
        if (userCreateDTO.getFio() == null || userCreateDTO.getFio().isBlank()){
            multipleErrorResponse.setErrors(new Error("FIO", "The field is not filled"));
        }
        if (userCreateDTO.getMail() == null || userCreateDTO.getMail().isBlank()) {
            multipleErrorResponse.setErrors(new Error("MAIL", "The field is not filled"));
        }
        if (userCreateDTO.getPassword() == null || userCreateDTO.getPassword().isBlank()) {
            multipleErrorResponse.setErrors(new Error("Password", "The field is not filled"));
        }
           // com.google.common.base.Enums.getIfPresent(UserRole.class, userCreateDTO.getRole()).orNull()
        if(! Arrays.stream(UserRole.values()).anyMatch(element -> element == userCreateDTO.getRole())  ){
            multipleErrorResponse.setErrors(new Error("Role", "Valid values: USER, ADMIN"));
        }
        if(! Arrays.stream(UserStatus.values()).anyMatch(element -> element == userCreateDTO.getStatus())  ){
            multipleErrorResponse.setErrors(
                    new Error("Status", "Valid values: WAITING_ACTIVATION, ACTIVATED, DEACTIVATED"));
        }

        if ( !multipleErrorResponse.getErrors().isEmpty()) {
            throw multipleErrorResponse;
        }
    }
}
