package com.connected.accountservice.domain.model.storagevalue;

import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.storage.Storage;

public final class StorageValueTestFactory {

    private StorageValueTestFactory() {

    }

    public static StorageValue createAnDefault() {
        return new StorageValueBuilder()
                .withId(StorageValueDefaultData.id)
                .withValue(StorageValueDefaultData.value)
                .withDate(StorageValueDefaultData.date)
                .withStorage(StorageValueDefaultData.storage)
                .build();
    }

    public static StorageValue createAnDefaultToInsert() {
        return new StorageValueBuilder()
                .withValue(StorageValueDefaultData.value)
                .withDate(StorageValueDefaultData.date)
                .withStorage(StorageValueDefaultData.storage)
                .build();
    }

    public static StorageValue createAnDefaultToInsertWithStorage(final Storage storage) {
        return new StorageValueBuilder()
                .withValue(StorageValueDefaultData.value)
                .withDate(StorageValueDefaultData.date)
                .withStorage(storage)
                .build();
    }

    public static StorageValue createAnDefaultToInsertWithStorageAndValue(final Storage storage,
                                                                          final Double value) {
        return new StorageValueBuilder()
                .withValue(value)
                .withDate(StorageValueDefaultData.date)
                .withStorage(storage)
                .build();
    }

    public static StorageValue createAnDefaultToInsertWithValue(final Double value) {
        return new StorageValueBuilder()
                .withValue(value)
                .withDate(StorageValueDefaultData.date)
                .withStorage(StorageValueDefaultData.storage)
                .build();
    }

    public static StorageValue createByMovement(final Movement movement) {
        return new StorageValueBuilder()
                .withValue(movement.getComputedValue())
                .withStorage(movement.getStorage())
                .build();
    }
}
