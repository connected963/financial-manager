package com.connected.accountservice.domain.model.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModelTestFactory;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import org.junit.Assert;
import org.junit.Test;

public class StorageValueBuilderTest {

    @Test
    public void givenAllData_mustCreateStorageValueWithSameData() {
        final var storageValueCreated = new StorageValueBuilder()
                .withId(StorageValueDefaultData.id)
                .withValue(StorageValueDefaultData.value)
                .withDate(StorageValueDefaultData.date)
                .withStorage(StorageValueDefaultData.storage)
                .build();

        final var expectedStorageValue = new StorageValue(
                StorageValueDefaultData.id,
                StorageValueDefaultData.value,
                StorageValueDefaultData.date,
                StorageValueDefaultData.storage);

        Assert.assertEquals(expectedStorageValue, storageValueCreated);
    }

    @Test
    public void givenStorageValueInputModel_mustCreateStorageValueWithSameData() {
        final var storageValueInputModel = StorageValueInputModelTestFactory.createAnDefaultToInsert();

        final var storageValueCreated = new StorageValueBuilder()
                .withStorageValueInputModel(storageValueInputModel)
                .build();

        final var expectedStorage = new StorageValue(
                storageValueInputModel.getId(),
                storageValueInputModel.getValue(),
                storageValueInputModel.getDate(),
                null);

        Assert.assertEquals(expectedStorage, storageValueCreated);
    }
}