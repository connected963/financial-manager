package com.connected.accountservice.application.inputmodel.monetarygoal;

import java.util.UUID;

public class MonetaryGoalInputModelTestBuilder {

    private UUID id;

    private Double value;

    private UUID storageId;

    public MonetaryGoalInputModelTestBuilder() {

    }

    public MonetaryGoalInputModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public MonetaryGoalInputModelTestBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public MonetaryGoalInputModelTestBuilder withStorageId(final UUID storageId) {
        this.storageId = storageId;
        return this;
    }

    public MonetaryGoalInputModel build() {
        return new MonetaryGoalInputModel(id, value, storageId);
    }
}
