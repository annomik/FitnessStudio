package by.it_academy.jd2.MJD29522.fitness.repositories.api;

import by.it_academy.jd2.MJD29522.fitness.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

//PagingAndSortingRepository
public interface IProductRepository extends ListCrudRepository<ProductEntity, UUID> {

    Page<ProductEntity> findAll(Pageable pageable);
    ProductEntity findByTitle(String title);
}
