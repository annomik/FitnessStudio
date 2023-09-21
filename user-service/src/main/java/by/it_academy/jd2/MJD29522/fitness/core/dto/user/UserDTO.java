package by.it_academy.jd2.MJD29522.fitness.core.dto.user;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import by.it_academy.jd2.MJD29522.fitness.service.converters.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID uuid ;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;

}
