package com.connected.accountservice.domain.querymodel.storage;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;

public final class StorageQueryModelTestFactory {

    private StorageQueryModelTestFactory() {

    }

    public static StorageQueryModel createAnDefault() {
        return new StorageQueryModelTestBuilder()
                .withId(StorageDefaultData.id)
                .withName(StorageDefaultData.name)
                .build();
    }

    public static StorageQueryModel createAnDefaultWithName(final String name) {
        return new StorageQueryModelTestBuilder()
                .withId(StorageDefaultData.id)
                .withName(name)
                .build();
    }
}
