package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.service.converters.serializers.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {

    private UUID uuid ;
    //@JsonDeserialize(using = LongToLDTDeserializer.class)
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtCreate;

   // @JsonDeserialize(converter = LongToLDTDeserializer.class)
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status ;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                   String mail, String fio, UserRole role, UserStatus status
                 ) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public UserDTO(String mail, String fio,
                   UserRole role, UserStatus status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
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

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
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
