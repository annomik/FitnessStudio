package by.it_academy.jd2.MJD29522.fitness.core.dto.food;

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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
