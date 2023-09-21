package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidEmail;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserLoginDTO {
    @ValidString
    @ValidEmail
    private String mail;
    @ValidString
    private String password;

}
