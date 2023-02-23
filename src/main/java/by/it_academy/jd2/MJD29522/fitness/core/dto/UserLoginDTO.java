package by.it_academy.jd2.MJD29522.fitness.core.dto;

import java.util.UUID;

public class UserLoginDTO {

    private UUID uuid;
    private String mail;
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(UUID uuid, String mail, String password) {
        this.uuid = uuid;
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



}
