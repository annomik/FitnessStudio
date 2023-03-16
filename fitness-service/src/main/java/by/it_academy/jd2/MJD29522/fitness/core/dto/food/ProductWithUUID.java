package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

import java.util.Objects;
import java.util.UUID;

public class ProductWithUUID {

    private UUID uuid;

    public ProductWithUUID() {
    }

    public ProductWithUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWithUUID that = (ProductWithUUID) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "ProductWithUUID{" +
                "uuid=" + uuid +
                '}';
    }
}
