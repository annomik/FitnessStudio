package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidEmail;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateDTO {

    private UUID uuid;
    @NumberFormat
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    @ValidEmail
    private String mail;
    @ValidString
    private String fio;
    @NotNull(message = "Valid values: USER, ADMIN")
    private UserRole role;
    @ValidString(message = "Valid values: WAITING_ACTIVATION, ACTIVATED, DEACTIVATED")
    private UserStatus status;
    @ValidString
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
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

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDTO that = (UserCreateDTO) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && role == that.role && status == that.status && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
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
