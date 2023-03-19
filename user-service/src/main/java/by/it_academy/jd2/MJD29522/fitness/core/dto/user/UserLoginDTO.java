package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import java.util.Objects;

public class UserLoginDTO {

    private String mail;
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String mail, String password) {
        this.mail = mail;
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

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return Objects.equals(mail, that.mail) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password);
    }
}
