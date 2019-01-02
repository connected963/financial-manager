package com.connected.accountservice.application.inputmodel.movement;

import com.connected.accountservice.domain.common.enums.MovementType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class MovementInputModel {

    @NotNull(message = "{movementUpdateInputModel.id.null}")
    private UUID id;

    @NotNull(message = "{movementUpdateInputModel.type.null}")
    private MovementType type;

    @NotNull(message = "{movementUpdateInputModel.value.null}")
    private Double value;

    private UUID storageId;

    @NotNull(message = "{movementUpdateInputModel.repeatMonthly.null}")
    private Boolean repeatMonthly;

    @NotBlank(message = "{movementUpdateInputModel.documentLinkedHash.blank}")
    @Length(max = 200, message = "{movementUpdateInputModel.documentLinkedHash.max}")
    private String documentLinkedHash;

    private MovementInputModel() {
    }

    MovementInputModel(@NotNull(message = "{movementUpdateInputModel.id.null}") final UUID id,
                       @NotNull(message = "{movementUpdateInputModel.type.null}") final MovementType type,
                       @NotNull(message = "{movementUpdateInputModel.value.null}") final Double value,
                       final UUID storageId,
                       @NotNull(message = "{movementUpdateInputModel.repeatMonthly.null}") final Boolean repeatMonthly,
                       @NotBlank(message = "{movementUpdateInputModel.documentLinkedHash.blank}")
                       @Length(max = 200, message = "{movementUpdateInputModel.documentLinkedHash.max}") final String documentLinkedHash) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.storageId = storageId;
        this.repeatMonthly = repeatMonthly;
        this.documentLinkedHash = documentLinkedHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementInputModel that = (MovementInputModel) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(value, that.value) &&
                Objects.equals(storageId, that.storageId) &&
                Objects.equals(repeatMonthly, that.repeatMonthly) &&
                Objects.equals(documentLinkedHash, that.documentLinkedHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, storageId, repeatMonthly, documentLinkedHash);
    }

    public boolean hasStorage() {
        return Objects.nonNull(storageId);
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

    public String getDocumentLinkedHash() {
        return documentLinkedHash;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("value", value)
                .append("storageId", storageId)
                .append("repeatMonthly", repeatMonthly)
                .append("documentLinkedHash", documentLinkedHash)
                .toString();
    }
}
