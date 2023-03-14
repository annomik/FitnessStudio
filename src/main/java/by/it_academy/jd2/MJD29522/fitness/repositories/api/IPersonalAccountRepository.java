package by.it_academy.jd2.MJD29522.fitness.repositories.api;

import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IPersonalAccountRepository extends CrudRepository<UserEntity, UUID> {

    UserEntity findByMail(String mail);

  //  long deleteByVerificationCode(String code);

//    @Modifying
//    @Query("delete from User user_code where user_code.code=:code")
//    void deleteCode(@Param("code") String code);
}
