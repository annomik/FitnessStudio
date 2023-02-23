package by.it_academy.jd2.MJD29522.fitness.entity;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(schema = "app", name = "user")
@SecondaryTable(schema = "app", name = "user_code",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid")
)
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinTable(
//            name="user_mail",
//            joinColumns= @JoinColumn(name="id"),
//            inverseJoinColumns=   @JoinColumn(name="mail")
//    )

    @Column(name = "mail")
    private String mail;

    @Column(name = "fio")
    private String fio;

    @Column(name = "role")
    private UserRole role;
    @Column(name = "status")
    private UserStatus status;

    @Column(name = "password")
    private String password;

    @Column(name = "code", table= "user_code")
    private int verificationCode;

    public UserEntity() {
    }

    public UserEntity(UserRegistrationDTO userRegistrationDTO) {
        this.uuid = UUID.randomUUID();
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
        this.mail = userRegistrationDTO.getMail();
        this.fio = userRegistrationDTO.getFio();
        this.role = UserRole.USER;
        this.status = UserStatus.WAITING_ACTIVATION;
        this.password = userRegistrationDTO.getPassword();
        this.verificationCode =  (int)  (Math.random() * 10000);
    }

    public UserEntity(UserCreateDTO userCreateDTO) {
        this.uuid = UUID.randomUUID();
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return Objects.equals(uuid, entity.uuid) && Objects.equals(dtCreate, entity.dtCreate) && Objects.equals(dtUpdate, entity.dtUpdate)
                && Objects.equals(mail, entity.mail) && Objects.equals(fio, entity.fio) && role == entity.role && status == entity.status && Objects.equals(password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", password='" + password + '\'' +

                '}';
    }
}
