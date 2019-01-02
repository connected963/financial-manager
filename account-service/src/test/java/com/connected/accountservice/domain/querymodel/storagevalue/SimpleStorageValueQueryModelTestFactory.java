package com.connected.accountservice.domain.querymodel.storagevalue;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;

public final class SimpleStorageValueQueryModelTestFactory {

    private SimpleStorageValueQueryModelTestFactory() {

    }

    public static SimpleStorageValueQueryModel createAnDefault() {
        return new SimpleStorageValueQueryModelTestBuilder()
                .withId(StorageValueDefaultData.id)
                .withValue(StorageValueDefaultData.value)
                .withDate(StorageValueDefaultData.date)
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static SimpleStorageValueQueryModel createAnDefaultWithValue(final Double value) {
        return new SimpleStorageValueQueryModelTestBuilder()
                .withId(StorageValueDefaultData.id)
                .withValue(value)
                .withDate(StorageValueDefaultData.date)
                .withStorageId(StorageDefaultData.id)
                .build();
    }
}
