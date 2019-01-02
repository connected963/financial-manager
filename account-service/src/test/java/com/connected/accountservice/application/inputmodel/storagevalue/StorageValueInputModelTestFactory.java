package com.connected.accountservice.application.inputmodel.storagevalue;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;

public final class StorageValueInputModelTestFactory {

    private StorageValueInputModelTestFactory() {

    }

    public static StorageValueInputModel createAnDefaultToInsert() {
        return new StorageValueInputModelTestBuilder()
                .withValue(StorageValueDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static StorageValueInputModel createAnDefaultToInsertWithoutValue() {
        return new StorageValueInputModelTestBuilder()
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static StorageValueInputModel createAnDefaultToInsertWithoutStorage() {
        return new StorageValueInputModelTestBuilder()
                .withValue(StorageValueDefaultData.value)
                .build();
    }

    public static StorageValueInputModel createAnDefaultToUpdate() {
        return new StorageValueInputModelTestBuilder()
                .withId(StorageValueDefaultData.id)
                .withValue(StorageValueDefaultData.value)
                .withDate(StorageValueDefaultData.date)
                .withStorageId(StorageDefaultData.id)
                .build();
    }
}
