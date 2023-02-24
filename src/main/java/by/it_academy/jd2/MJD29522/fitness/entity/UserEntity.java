package by.it_academy.jd2.MJD29522.fitness.entity;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

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

    @Column(name = "mail")
    private String mail;

    @Column(name = "fio")
    private String fio;

    @Column(name = "status")
    private UserStatus status;

    @Column(name = "password")
    private String password;

    @Column(name = "code", table= "user_code")
    private int verificationCode;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "app",  name = "user_role",
            joinColumns=
            @JoinColumn(name="uuid"),
            inverseJoinColumns=
            @JoinColumn(name="role")
    )
    private RoleEntity roleEntity;

    public UserEntity() {
    }

    public UserEntity(UserRegistrationDTO userRegistrationDTO) {
        this.uuid = UUID.randomUUID();
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
        this.mail = userRegistrationDTO.getMail();
        this.fio = userRegistrationDTO.getFio();
        this.status = UserStatus.WAITING_ACTIVATION;
        this.password = userRegistrationDTO.getPassword();
        this.roleEntity = getRoleEntity();

    }

    //    public UserEntity(UserRegistrationDTO userRegistrationDTO) {
//        this.uuid = UUID.randomUUID();
//        this.dtCreate = LocalDateTime.now();
//        this.dtUpdate = LocalDateTime.now();
//        this.mail = userRegistrationDTO.getMail();
//        this.fio = userRegistrationDTO.getFio();
//        this.role = UserRole.USER;
//        this.status = UserStatus.WAITING_ACTIVATION;
//        this.password = userRegistrationDTO.getPassword();
//        this.verificationCode =  (int)  (Math.random() * 10000);
//    }

    public UserEntity(UserCreateDTO userCreateDTO) {
        this.uuid = UUID.randomUUID();
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
        this.mail = mail;
        this.fio = fio;
       // this.role = role;
        this.status = status;
        this.password = password;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
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
