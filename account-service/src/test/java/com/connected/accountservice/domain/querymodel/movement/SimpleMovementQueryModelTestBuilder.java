package com.connected.accountservice.domain.querymodel.movement;

import com.connected.accountservice.domain.common.enums.MovementType;

import java.util.UUID;

public class SimpleMovementQueryModelTestBuilder {

    private UUID id;

    private MovementType type;

    private Double value;

    private UUID storageId;

    private Boolean repeatMonthly;

    public SimpleMovementQueryModelTestBuilder() {

    }

    public SimpleMovementQueryModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public SimpleMovementQueryModelTestBuilder withType(final MovementType type) {
        this.type = type;
        return this;
    }

    public SimpleMovementQueryModelTestBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public SimpleMovementQueryModelTestBuilder withStorageId(final UUID storageId) {
        this.storageId = storageId;
        return this;
    }

    public SimpleMovementQueryModelTestBuilder withRepeatMonthly(final Boolean repeatMonthly) {
        this.repeatMonthly = repeatMonthly;
        return this;
    }

    public SimpleMovementQueryModel build() {
        return new SimpleMovementQueryModel(id, type, value, storageId, repeatMonthly);
    }
}
