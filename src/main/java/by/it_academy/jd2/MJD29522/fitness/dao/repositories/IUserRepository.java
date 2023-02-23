package by.it_academy.jd2.MJD29522.fitness.dao.repositories;


import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface IUserRepository extends CrudRepository<UserEntity, UUID> {
  List<UserEntity> findAll();

}
