package com.connected.accountservice.domain.model.monetarygoal;

import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;

public final class MonetaryGoalTestFactory {

    private MonetaryGoalTestFactory() {

    }

    public static MonetaryGoal createAnDefault() {
        return new MonetaryGoalBuilder()
                .withId(MovementDefaultData.id)
                .withValue(MovementDefaultData.value)
                .withStorage(StorageTestFactory.createAnDefault())
                .build();
    }

    public static MonetaryGoal createAnDefaultToInsertWithStorage(final Storage storage) {
        return new MonetaryGoalBuilder()
                .withValue(MovementDefaultData.value)
                .withStorage(storage)
                .build();
    }
}
