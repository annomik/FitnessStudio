package by.it_academy.jd2.MJD29522.fitness.entity;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "user")
@SecondaryTable(schema = "fitness", name = "user_code",
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
    private int verificationCode;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "fitness", name = "user_role",
            joinColumns=
            @JoinColumn(name="user_uuid"),
            inverseJoinColumns=
            @JoinColumn(name="role_id")
    )
    private RoleEntity roleEntity;

    public UserEntity() {
    }

    //UserRegistrationDTO
    public UserEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                      String mail, String fio, StatusEntity statusEntity, String password,
                      int verificationCode, RoleEntity roleEntity) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.statusEntity = statusEntity;
        this.password = password;
        this.verificationCode = verificationCode;
        this.roleEntity = roleEntity;
    }

    //UserDTO
    public UserEntity(UUID uuid, LocalDateTime dtCreate,
                      LocalDateTime dtUpdate, String mail,
                      String fio,
                      RoleEntity roleEntity,
                      StatusEntity statusEntity) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.roleEntity = roleEntity;
        this.statusEntity = statusEntity;
    }

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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
