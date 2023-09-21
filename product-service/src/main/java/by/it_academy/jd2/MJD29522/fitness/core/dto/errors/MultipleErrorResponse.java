package by.it_academy.jd2.MJD29522.fitness.core.dto.errors;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MultipleErrorResponse {

    private ErrorCode logref;
    private List<LocalError> errors = new ArrayList<>();

}
