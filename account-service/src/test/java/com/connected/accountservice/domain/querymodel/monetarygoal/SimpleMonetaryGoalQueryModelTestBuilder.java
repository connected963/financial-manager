package com.connected.accountservice.domain.querymodel.monetarygoal;

import java.util.UUID;

public class SimpleMonetaryGoalQueryModelTestBuilder {

    private UUID id;

    private Double value;

    private UUID storageId;

    public SimpleMonetaryGoalQueryModelTestBuilder() {

    }

    public SimpleMonetaryGoalQueryModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public SimpleMonetaryGoalQueryModelTestBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public SimpleMonetaryGoalQueryModelTestBuilder withStorageId(final UUID storageId) {
        this.storageId = storageId;
        return this;
    }

    public SimpleMonetaryGoalQueryModel build() {
        return new SimpleMonetaryGoalQueryModel(id, value, storageId);
    }
}
