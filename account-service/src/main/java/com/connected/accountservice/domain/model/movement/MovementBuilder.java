package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModel;
import com.connected.accountservice.domain.common.enums.MovementType;
import com.connected.accountservice.domain.model.storage.Storage;

import java.util.UUID;

class MovementBuilder {

    private UUID id;

    private MovementType type;

    private Double value;

    private Storage storage;

    private Boolean repeatMonthly;

    private String documentLinkedHash;

    MovementBuilder() {

    }

    MovementBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    MovementBuilder withType(final MovementType type) {
        this.type = type;
        return this;
    }

    MovementBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    MovementBuilder withStorage(final Storage storage) {
        this.storage = storage;
        return this;
    }

    MovementBuilder withRepeatMonthly(final Boolean repeatMonthly) {
        this.repeatMonthly = repeatMonthly;
        return this;
    }

    MovementBuilder withDocumentLinkedHash(final String documentLinkedHash) {
        this.documentLinkedHash = documentLinkedHash;
        return this;
    }

    MovementBuilder withMovementInputModel(
            final MovementInputModel movementInputModel) {
        this.id = movementInputModel.getId();
        this.type = movementInputModel.getType();
        this.value = movementInputModel.getValue();
        this.repeatMonthly = movementInputModel.getRepeatMonthly();
        this.documentLinkedHash = movementInputModel.getDocumentLinkedHash();
        return this;
    }

    Movement build() {
        return new Movement(id, type, value, storage, repeatMonthly, documentLinkedHash);
    }
}
