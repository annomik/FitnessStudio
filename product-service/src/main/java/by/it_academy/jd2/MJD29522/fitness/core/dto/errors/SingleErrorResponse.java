package by.it_academy.jd2.MJD29522.fitness.core.dto.errors;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SingleErrorResponse {
    private ErrorCode logref;
    private String message;

}