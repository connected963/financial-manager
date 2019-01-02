package com.connected.accountservice.domain.model.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModel;

public class StorageFactory {

    private StorageFactory() {

    }

    public static Storage createByInputModel(final StorageInputModel storageInputModel) {
        return new StorageBuilder()
                .withStorageInputModel(storageInputModel)
                .build();
    }
}
