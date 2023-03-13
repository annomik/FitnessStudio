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
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.*;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IPersonalAccountService personalAccountService;
    private final IConversionToDTO conversionToDTO;
    private final IConversionToEntity conversionToEntity;

    public UserService(IUserRepository userRepository,
                       IPersonalAccountService personalAccountService,
                       IConversionToDTO conversionToDTO,
                       IConversionToEntity conversionToEntity) {
        this.userRepository = userRepository;
        this.personalAccountService = personalAccountService;
        this.conversionToDTO = conversionToDTO;
        this.conversionToEntity = conversionToEntity;
    }

    @Override
    public void addNewUser(UserCreateDTO userCreateDTO) {
        validate(userCreateDTO);
        UserEntity entity = conversionToEntity.convertToEntity(userCreateDTO);
        userRepository.save(entity);
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDTO userCreateDTO) {
        if(uuid == null || userCreateDTO == null){
            throw new SingleErrorResponse("Введите параметры для обновления");
        }
        validate(userCreateDTO);
        Optional<UserEntity> userEntityFromDB = userRepository.findById(uuid);
        if (userEntityFromDB.isEmpty()) {
            throw new SingleErrorResponse("Пользователя с id " + uuid + " для обновления не найдено!");
        } else {
            UserEntity entity = userEntityFromDB.get();
            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid) ) {
                entity.setMail(userCreateDTO.getMail());
                entity.setFio(userCreateDTO.getFio());
                entity.setRoleEntity(new RoleEntity(userCreateDTO.getRole()));
                entity.setStatusEntity(new StatusEntity(userCreateDTO.getStatus()));
                entity.setPassword(userCreateDTO.getPassword());
                userRepository.save(entity);
            }else{
                throw new SingleErrorResponse("Версии для пользователя не совпадают!");
            }
        }
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        return personalAccountService.getCard(uuid);
    }

    @Override
    public PageDTO<UserDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<UserEntity> allEntity = userRepository.findAll(pageable);
        List<UserDTO> content = new ArrayList<>();

        for (UserEntity entity: allEntity) {
            UserDTO userDTO = conversionToDTO.convertToDTO(entity);
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

        if (userCreateDTO.getFio() == null || userCreateDTO.getFio().isBlank()){
            multipleErrorResponse.setErrors(new Error("FIO", "Поле не заполнено"));
        }
        if (userCreateDTO.getMail() == null || userCreateDTO.getMail().isBlank()) {
            multipleErrorResponse.setErrors(new Error("MAIL", "Поле не заполнено"));
        }
        UserEntity userEntity = userRepository.findByMail(userCreateDTO.getMail());
        if(userEntity != null){
            multipleErrorResponse.setErrors(new Error("MAIL","Пользователь с таким MAIL уже существует"));
        }

        if (userCreateDTO.getPassword() == null || userCreateDTO.getPassword().isBlank()) {
            multipleErrorResponse.setErrors(new Error("Password", "Поле не заполнено"));
        }
           // com.google.common.base.Enums.getIfPresent(UserRole.class, userCreateDTO.getRole()).orNull()
        if(! Arrays.stream(UserRole.values()).anyMatch(element -> element == userCreateDTO.getRole())  ){
            multipleErrorResponse.setErrors(new Error("Role", "Допустимые значения: USER, ADMIN"));
        }
        if(! Arrays.stream(UserStatus.values()).anyMatch(element -> element == userCreateDTO.getStatus())  ){
            multipleErrorResponse.setErrors(
                    new Error("Status", "Допустимые значения: WAITING_ACTIVATION, ACTIVATED, DEACTIVATED"));
        }

        if ( !multipleErrorResponse.getErrors().isEmpty()) {
            throw multipleErrorResponse;
        }
    }
}
