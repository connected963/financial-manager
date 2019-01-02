package com.connected.accountservice.domain.model.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModelTestFactory;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import org.junit.Assert;
import org.junit.Test;

public class StorageBuilderTest {

    @Test
    public void givenAllData_mustCreateStorageWithSameData() {
        final var storage = new StorageBuilder()
                .withId(StorageDefaultData.id)
                .withName(StorageDefaultData.name)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();

        final var expectedStorage = new Storage(
                StorageDefaultData.id,
                StorageDefaultData.name,
                StorageDefaultData.documentLinkedHash);

        Assert.assertEquals(expectedStorage, storage);
    }

    @Test
    public void givenStorageInputModel_mustCreateStorageWithSameData() {
        final var storageInputModel = StorageInputModelTestFactory.createAnDefaultToUpdate();

        final var storageCreated = new StorageBuilder()
                .withStorageInputModel(storageInputModel)
                .build();

        final var expectedStorage = new Storage(
                storageInputModel.getId(),
                storageInputModel.getName(),
                storageInputModel.getDocumentLinkedHash());

        Assert.assertEquals(expectedStorage, storageCreated);
    }
}