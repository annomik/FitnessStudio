package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.BlankFieldException;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.repositories.IUserRepository;
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
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    public boolean addNewUser(UserCreateDTO userCreateDTO) {
        if (validate(userCreateDTO)) {
            UserEntity entity = conversionToEntity.convertToEntity(userCreateDTO);
            userRepository.save(entity);
            return true;
        } else
            return false;
    }

    @Override
    public void update(UUID uuid, long dtUpdate, UserCreateDTO userCreateDTO) {
        Optional<UserEntity> findUserEntity = userRepository.findById(uuid);

        if (!findUserEntity.isEmpty()) {
            UserEntity entity = findUserEntity.get();
            long epochMilli = ZonedDateTime.of(entity.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli();

            if ( epochMilli == dtUpdate && entity.getUuid().equals(uuid) ) {
                entity.setDtUpdate(LocalDateTime.now());
                entity.setMail(userCreateDTO.getMail());
                entity.setFio(userCreateDTO.getFio());
                entity.setRoleEntity(new RoleEntity(userCreateDTO.getRole()));
                entity.setStatusEntity(new StatusEntity(userCreateDTO.getStatus()));
                entity.setPassword(userCreateDTO.getPassword());
                userRepository.save(entity);
            }else{
                throw new SingleErrorResponse("Версии для пользователя с id " + uuid +" не совпадают!");
            }
        } else {
            throw new SingleErrorResponse("Пользователя с id " + uuid + " для обновления не найдено!");
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

        return new PageDTO<UserDTO>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content);
    }

    public boolean validate(UserCreateDTO userCreateDTO)  {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();
//        if(userCreateDTO == null){
//            throw new SingleErrorResponse("НЕ переданы данные");
//        } else {
            if (userCreateDTO.getFio() == null || userCreateDTO.getFio().isBlank()){
                multipleErrorResponse.setErrors(new Error("FIO", "Поле не заполнено"));
                throw new BlankFieldException("FIO");
            }
            if (userCreateDTO.getMail() == null || userCreateDTO.getMail().isBlank()) {
                multipleErrorResponse.setLogref("some_error");
                multipleErrorResponse.setErrors(new Error("MAIL", "Поле не заполнено"));
                throw new BlankFieldException("Поле не заполнено");

            }
     //   }
        if (multipleErrorResponse == null) {
            return true;
        }
       // throw multipleErrorResponse;
        return false;
    }
}
