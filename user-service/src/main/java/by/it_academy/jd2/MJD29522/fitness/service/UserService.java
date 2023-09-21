package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.InputSingleDataException;
import by.it_academy.jd2.MJD29522.fitness.entity.RoleEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.StatusEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.enums.ErrorCode;
import by.it_academy.jd2.MJD29522.fitness.repositories.api.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.converters.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IPersonalAccountService personalAccountService;
    private final ConversionService conversionService;
    private final IConversionToEntity conversionToEntity;
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public void addNewUser(@NotNull @Valid UserCreateDTO userCreateDTO) {

        UserEntity userEntity = userRepository.findByMail(userCreateDTO.getMail());
        if(userEntity != null){
            throw new InputSingleDataException("User with this email address already exists", ErrorCode.ERROR);
        }
        UserEntity entity = conversionToEntity.convertToEntity(userCreateDTO);
        String encode = encoder.encode(entity.getPassword());
        entity.setPassword(encode);
        userRepository.save(entity);
    }

    @Override
    public void update(@ValidString UUID uuid, @NotNull LocalDateTime dtUpdate, @NotNull @Valid  UserCreateDTO userCreateDTO) {
        Optional<UserEntity> userEntityFromDB = userRepository.findById(uuid);
        if (userEntityFromDB.isEmpty()) {
            throw new InputSingleDataException("User with id " + uuid + " for update not found.", ErrorCode.ERROR);
        }
        UserEntity entity = userEntityFromDB.get();
        if (!entity.getDtUpdate().isEqual(dtUpdate) ) {
            throw new InputSingleDataException("Versions for the user with id " + uuid + " do not match", ErrorCode.ERROR);
        }
        String encode = encoder.encode(userCreateDTO.getPassword());
        entity.setMail(userCreateDTO.getMail());
        entity.setFio(userCreateDTO.getFio());
        entity.setRoleEntity(new RoleEntity(userCreateDTO.getRole()));
        entity.setStatusEntity(new StatusEntity(userCreateDTO.getStatus()));
        entity.setPassword(encode);
        userRepository.save(entity);
    }

    @Override
    public UserDTO getCard(UUID uuid) {
        return personalAccountService.getCard(uuid);
    }

    @Override
    public UserDTO getUserByMail(String mail) {
        UserEntity userEntity = userRepository.findByMail(mail);

        return conversionService.convert(userEntity, UserDTO.class);
    }

    @Override
    public PageDTO<UserDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<UserEntity> allEntity = userRepository.findAll(pageable);
        List<UserDTO> content = allEntity.getContent().stream()
                .map(s -> conversionService.convert(s, UserDTO.class))
                .collect(Collectors.toList());

        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content);
    }

}
