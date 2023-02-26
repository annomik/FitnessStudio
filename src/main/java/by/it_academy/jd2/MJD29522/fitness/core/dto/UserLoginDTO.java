package by.it_academy.jd2.MJD29522.fitness.core.dto;

public class UserLoginDTO {

    private String mail;
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public void setPassword(String password) {
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
}
