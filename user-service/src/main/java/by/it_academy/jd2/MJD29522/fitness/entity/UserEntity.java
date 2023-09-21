package by.it_academy.jd2.MJD29522.fitness.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "fitness", name = "user")
@SecondaryTable(schema = "fitness", name = "user_code",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid")
)
public class UserEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_create", updatable = false)
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "mail")
    private String mail;

    @Column(name = "fio")
    private String fio;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "fitness", name = "user_status",
            joinColumns=
            @JoinColumn(name="user_uuid"),
            inverseJoinColumns=
            @JoinColumn(name="status_id")
    )
    private StatusEntity statusEntity;

    @Column(name = "password")
    private String password;

    @Column(name = "code", table= "user_code")
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "fitness", name = "user_role",
            joinColumns=
            @JoinColumn(name="user_uuid"),
            inverseJoinColumns=
            @JoinColumn(name="role_id")
    )
    private RoleEntity roleEntity;


    //UserRegistrationDTO
//    public UserEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
//                      String mail, String fio, StatusEntity statusEntity, String password,
//                      String verificationCode, RoleEntity roleEntity) {
//        this.uuid = uuid;
//        this.dtCreate = dtCreate;
//        this.dtUpdate = dtUpdate;
//        this.mail = mail;
//        this.fio = fio;
//        this.statusEntity = statusEntity;
//        this.password = password;
//        this.verificationCode = verificationCode;
//        this.roleEntity = roleEntity;
//    }

    //UserCreateDTO
    public UserEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                      String mail, String fio, StatusEntity statusEntity, String password,
                      RoleEntity roleEntity) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.statusEntity = statusEntity;
        this.password = password;
        this.roleEntity = roleEntity;
    }
}
