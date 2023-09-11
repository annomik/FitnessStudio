package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidEmail;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;

import java.util.Objects;

public class UserRegistrationDTO {

    @ValidEmail
    private String mail;
    @ValidString
    private String fio;
    @ValidString
    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationDTO that = (UserRegistrationDTO) o;
        return Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, fio, password);
    }
}
