package com.connected.accountservice.domain.querymodel.movement;

import com.connected.accountservice.domain.common.enums.MovementType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class SimpleMovementQueryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private MovementType type;

    private Double value;

    private UUID storageId;

    private Boolean repeatMonthly;

    public SimpleMovementQueryModel(final UUID id,
                                    final MovementType type,
                                    final Double value,
                                    final UUID storageId,
                                    final Boolean repeatMonthly) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.storageId = storageId;
        this.repeatMonthly = repeatMonthly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleMovementQueryModel that = (SimpleMovementQueryModel) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(value, that.value) &&
                Objects.equals(storageId, that.storageId) &&
                Objects.equals(repeatMonthly, that.repeatMonthly);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, storageId, repeatMonthly);
    }

    public UUID getId() {
        return id;
    }

    public MovementType getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public UUID getStorageId() {
        return storageId;
    }

    public Boolean getRepeatMonthly() {
        return repeatMonthly;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("value", value)
                .append("storageId", storageId)
                .append("repeatMonthly", repeatMonthly)
                .toString();
    }
}
