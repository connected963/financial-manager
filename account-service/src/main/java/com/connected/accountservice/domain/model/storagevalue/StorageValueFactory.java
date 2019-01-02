package com.connected.accountservice.domain.model.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModel;
import com.connected.accountservice.domain.model.storage.Storage;

public class StorageValueFactory {

    private StorageValueFactory() {

    }

    public static StorageValue createNew(final Storage storage) {
        return new StorageValueBuilder()
                .withStorage(storage)
                .build();
    }

    public static StorageValue createByInputModelWithStorage(
            final StorageValueInputModel storageValueInputModel,
            final Storage storage) {

        return new StorageValueBuilder()
                .withStorageValueInputModel(storageValueInputModel)
                .withStorage(storage)
                .build();
    }
}
