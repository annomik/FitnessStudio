package by.it_academy.jd2.MJD29522.fitness.entity;

import by.it_academy.jd2.MJD29522.fitness.enums.UserStatus;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "fitness", name = "status")
public class StatusEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public StatusEntity() {
    }

    public StatusEntity(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusEntity{" +
                "status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
