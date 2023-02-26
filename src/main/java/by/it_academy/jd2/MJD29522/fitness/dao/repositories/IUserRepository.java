package by.it_academy.jd2.MJD29522.fitness.dao.repositories;

import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

//PagingAndSortingRepository
public interface IUserRepository extends ListCrudRepository<UserEntity, UUID> {

    Page<UserEntity> findAll(Pageable pageable);
}
