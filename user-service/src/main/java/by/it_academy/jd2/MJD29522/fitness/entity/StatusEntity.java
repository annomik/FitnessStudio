package by.it_academy.jd2.MJD29522.fitness.entity;

import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "fitness", name = "status")
public class StatusEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
