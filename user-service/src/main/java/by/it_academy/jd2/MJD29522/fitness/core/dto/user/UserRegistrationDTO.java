package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidEmail;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserRegistrationDTO {

    @ValidEmail
    private String mail;
    @ValidString
    private String fio;
    @ValidString
    private String password;

}
