package com.connected.accountservice.domain.model.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModel;
import com.connected.accountservice.domain.model.storage.Storage;

import java.util.UUID;

class MonetaryGoalBuilder {

    private UUID id;

    private Double value;

    private Storage storage;

    MonetaryGoalBuilder() {

    }

    MonetaryGoalBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    MonetaryGoalBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    MonetaryGoalBuilder withStorage(final Storage storage) {
        this.storage = storage;
        return this;
    }

    MonetaryGoalBuilder withMonetaryGoalInputModel(
            final MonetaryGoalInputModel monetaryGoalInputModel) {
        this.id = monetaryGoalInputModel.getId();
        this.value = monetaryGoalInputModel.getValue();
        return this;
    }

    MonetaryGoal build() {
        return new MonetaryGoal(id, value, storage);
    }
}
