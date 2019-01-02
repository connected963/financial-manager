package com.connected.accountservice.application.inputmodel.movement;

import com.connected.accountservice.domain.common.enums.MovementType;

import java.util.UUID;

public class MovementInputModelTestBuilder {

    private UUID id;

    private MovementType type;

    private Double value;

    private UUID storageId;

    private Boolean repeatMonthly;

    private String documentLinkedHash;

    public MovementInputModelTestBuilder() {

    }

    public MovementInputModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public MovementInputModelTestBuilder withType(final MovementType type) {
        this.type = type;
        return this;
    }

    public MovementInputModelTestBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public MovementInputModelTestBuilder withStorageId(final UUID storageId) {
        this.storageId = storageId;
        return this;
    }

    public MovementInputModelTestBuilder withRepeatMonthly(final Boolean repeatMonthly) {
        this.repeatMonthly = repeatMonthly;
        return this;
    }

    public MovementInputModelTestBuilder withDocumentLinkedHash(final String documentLinkedHash) {
        this.documentLinkedHash = documentLinkedHash;
        return this;
    }

    public MovementInputModel build() {
        return new MovementInputModel(id, type, value, storageId, repeatMonthly, documentLinkedHash);
    }
}
