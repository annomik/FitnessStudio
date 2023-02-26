package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.dao.repositories.IUserRepository;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IConversionToDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IConversionToEntity;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        UserEntity entity = conversionToEntity.convertToEntity(userCreateDTO);
        userRepository.save(entity);
        return true;
    }


    @Override
    public void update(UUID uuid, long dtUpdate, UserCreateDTO userCreateDTO) {
     //   if (personalAccountService.getCard(uuid) != null){
//         UserEntity userEntity = userRepository.find
//         if (dtUpdate == user)
//            UserDTO userFromDB = personalAccountService.getCard(uuid);
//            if (userFromDB.getDtUpdate().equals(dtUpdate))
//                UserCreateDTO userCreateDTO =
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
                content  );
    }
}
