package by.it_academy.jd2.MJD29522.fitness.core.dto.errors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class LocalError {
    private String field;
    private String message;
}
