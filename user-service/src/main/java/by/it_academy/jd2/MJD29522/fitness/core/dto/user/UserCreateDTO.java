package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidEmail;
import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

  //  private UUID uuid;
    @NumberFormat
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    @ValidEmail
    private String mail;
    @ValidString
    private String fio;
    @NotNull(message = "Valid values: USER, ADMIN")
    private UserRole role;
    @NotNull(message = "Valid values: WAITING_ACTIVATION, ACTIVATED, DEACTIVATED")
    private UserStatus status;
    @ValidString
    private String password;


}
