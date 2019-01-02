package com.connected.accountservice.domain.model.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModelTestFactory;
import org.junit.Assert;
import org.junit.Test;

public class StorageFactoryTest {

    @Test
    public void givenInputModel_mustCreateStorageWithSameData() {
        final var storageInputModel = StorageInputModelTestFactory.createAnDefaultToUpdate();

        final var storageCreated = StorageFactory.createByInputModel(storageInputModel);

        final var expectedStorage = StorageTestFactory.createAnDefault();

        Assert.assertEquals(expectedStorage, storageCreated);
    }

}