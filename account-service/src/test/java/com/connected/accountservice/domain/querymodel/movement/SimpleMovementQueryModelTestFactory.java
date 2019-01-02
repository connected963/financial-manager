package com.connected.accountservice.domain.querymodel.movement;

import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;

public final class SimpleMovementQueryModelTestFactory {

    private SimpleMovementQueryModelTestFactory() {

    }

    public static SimpleMovementQueryModel createAnDefault() {
        return new SimpleMovementQueryModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .build();
    }

    public static SimpleMovementQueryModel createAnDefaultWithValue(final Double value) {
        return new SimpleMovementQueryModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .build();
    }
}
