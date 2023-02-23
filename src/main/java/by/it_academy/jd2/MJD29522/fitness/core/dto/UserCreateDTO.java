package by.it_academy.jd2.MJD29522.fitness.core.dto;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserCreateDTO {

    private UUID uuid = UUID.randomUUID();

    private Long dtСreate;
    private Long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role= UserRole.USER;
    private UserStatus status = UserStatus.ACTIVATED;
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(UUID uuid, Long dtСreate, Long dtUpdate,
                         String mail, String fio, UserRole role, UserStatus status
                        ) {
        this.uuid = uuid;
        this.dtСreate = dtСreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public UserCreateDTO( String mail, String fio,
                         UserRole role, UserStatus status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public Long getDtСreate() {
        return dtСreate;
    }

    public void setDtСreate(Long dtСreate) {
        this.dtСreate = dtСreate;
    }

    public Long getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getFio() {
        return fio;
    }


}
