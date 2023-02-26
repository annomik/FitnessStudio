package by.it_academy.jd2.MJD29522.fitness.dao.repositories;

import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends PagingAndSortingRepository<UserEntity, UUID> {

   // Optional<UserEntity> findByMail(String mail);
}
