package by.it_academy.jd2.MJD29522.fitness.entity;

import by.it_academy.jd2.MJD29522.fitness.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "fitness", name = "role")
public class RoleEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
