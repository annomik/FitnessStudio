package by.it_academy.jd2.MJD29522.fitness.repositories;

import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

//PagingAndSortingRepository
public interface IProductRepository extends ListCrudRepository<ProductEntity, UUID> {

    Page<ProductEntity> findAll(Pageable pageable);
}
