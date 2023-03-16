package by.it_academy.jd2.MJD29522.fitness.repositories.api;

import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;


public interface IUserRepository extends ListCrudRepository<UserEntity, UUID> {

    Page<UserEntity> findAll(Pageable pageable);
    UserEntity findByMail(String mail);

}
