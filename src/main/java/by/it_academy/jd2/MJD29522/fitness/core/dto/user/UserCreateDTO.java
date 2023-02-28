package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateDTO {

    private UUID uuid;

    private Long dtCreate;
    private Long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(UUID uuid, Long dtCreate, Long dtUpdate,
                         String mail, String fio, UserRole role, UserStatus status,
                         String password ) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }

    public Long getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
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
