package com.connected.accountservice.application.inputmodel.monetarygoal;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class MonetaryGoalInputModel {

    @NotNull(message = "{monetaryGoalInputModel.id.null}")
    private UUID id;

    @NotNull(message = "{monetaryGoalInputModel.value.null}")
    @Min(value = 0, message = "{monetaryGoalInputModel.value.min}")
    private Double value;

    @NotNull(message = "{monetaryGoalInputModel.storage.null}")
    private UUID storageId;

    private MonetaryGoalInputModel() {

    }


    MonetaryGoalInputModel(@NotNull(message = "{monetaryGoalInputModel.id.null}") final UUID id,
                           @NotNull(message = "{monetaryGoalInputModel.value.null}")
                           @Min(value = 0, message = "{monetaryGoalInputModel.value.min}") final Double value,
                           @NotNull(message = "{monetaryGoalInputModel.storage.null}") final UUID storageId) {
        this.id = id;
        this.value = value;
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonetaryGoalInputModel that = (MonetaryGoalInputModel) o;
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
