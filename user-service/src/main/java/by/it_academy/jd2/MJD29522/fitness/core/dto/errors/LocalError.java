package by.it_academy.jd2.MJD29522.fitness.core.dto.errors;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class LocalError {
    private String field;
    private String message;
}
