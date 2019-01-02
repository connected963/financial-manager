package com.connected.accountservice.domain.model.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModelTestFactory;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import org.junit.Assert;
import org.junit.Test;

public class StorageValueFactoryTest {

    @Test
    public void givenStorage_mustCreateStorageValueWithStorage() {
        final var storage = StorageTestFactory.createAnDefault();

        final var storageValueCreated = StorageValueFactory.createNew(storage);

        final var storageValueExpected = new StorageValueBuilder()
                .withStorage(storage)
                .build();

        Assert.assertEquals(storageValueExpected, storageValueCreated);
    }

    @Test
    public void givenStorageValueInputModelAndStorage_mustCreateStorageValueMatching() {
        final var storageValueInputModel = StorageValueInputModelTestFactory.createAnDefaultToInsert();
        final var storage = StorageTestFactory.createAnDefault();

        final var storageValueCreated =
                StorageValueFactory.createByInputModelWithStorage(storageValueInputModel, storage);

        final var storageValueExpected = new StorageValueBuilder()
                .withStorageValueInputModel(storageValueInputModel)
                .withStorage(storage)
                .build();

        Assert.assertEquals(storageValueExpected, storageValueCreated);
    }

}