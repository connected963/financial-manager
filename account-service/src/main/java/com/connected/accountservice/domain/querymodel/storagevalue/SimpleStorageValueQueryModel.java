package com.connected.accountservice.domain.querymodel.storagevalue;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SimpleStorageValueQueryModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    private UUID id;

    private Double value;

    private LocalDateTime date;

    private UUID storageId;

    public SimpleStorageValueQueryModel(final UUID id, final Double value,
                                        final LocalDateTime date, final UUID storageId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleStorageValueQueryModel that = (SimpleStorageValueQueryModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(date, that.date) &&
                Objects.equals(storageId, that.storageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date, storageId);
    }

    public UUID getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public UUID getStorageId() {
        return storageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("value", value)
                .append("date", date)
                .append("storageId", storageId)
                .toString();
    }
}
