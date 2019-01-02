package com.connected.accountservice.domain.querymodel.monetarygoal;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class SimpleMonetaryGoalQueryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private Double value;

    private UUID storageId;

    public SimpleMonetaryGoalQueryModel(final UUID id, final Double value, final UUID storageId) {
        this.id = id;
        this.value = value;
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleMonetaryGoalQueryModel that = (SimpleMonetaryGoalQueryModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(storageId, that.storageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, storageId);
    }

    public UUID getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public UUID getStorageId() {
        return storageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("value", value)
                .append("storageId", storageId)
                .toString();
    }
}
