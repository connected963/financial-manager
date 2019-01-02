package com.connected.accountservice.application.inputmodel.storagevalue;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class StorageValueInputModel {

    private UUID id;

    @NotNull(message = "{storageValueInputModel.value.null}")
    @Min(value = 0, message = "{storageValueInputModel.value.min}")
    private Double value;

    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;

    @NotNull(message = "{storageValueInputModel.storageId.null}")
    private UUID storageId;

    private StorageValueInputModel() {
    }

    StorageValueInputModel(@NotNull(message = "{storageValueInputModel.id.null}") final UUID id,
                           @NotNull(message = "{storageValueInputModel.value.null}")
                           @Min(value = 0, message = "{storageValueInputModel.value.min}") final Double value,
                           @NotNull(message = "{storageValueInputModel.date.null}") final LocalDateTime date,
                           @NotNull(message = "{storageValueInputModel.storageId.null}") final UUID storageId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageValueInputModel that = (StorageValueInputModel) o;
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
