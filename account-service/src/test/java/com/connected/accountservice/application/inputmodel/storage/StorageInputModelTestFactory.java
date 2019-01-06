package com.connected.accountservice.application.inputmodel.storage;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;

public final class StorageInputModelTestFactory {

    private StorageInputModelTestFactory() {

    }

    public static StorageInputModel createAnDefaultToInsert() {
        return new StorageInputModelTestBuilder()
                .withName(StorageDefaultData.name)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static StorageInputModel createAnDefaultToInsertWithoutName() {
        return new StorageInputModelTestBuilder()
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static StorageInputModel createAnDefaultToInsertWithoutDocumentLinkedHash() {
        return new StorageInputModelTestBuilder()
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static StorageInputModel createAnDefaultToUpdate() {
        return new StorageInputModelTestBuilder()
                .withId(StorageDefaultData.id)
                .withName(StorageDefaultData.name)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static StorageInputModel createAnDefaultToUpdateWithoutName() {
        return new StorageInputModelTestBuilder()
                .withId(StorageDefaultData.id)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static StorageInputModel createAnDefaultToUpdateWithoutDocumentLinkedHash() {
        return new StorageInputModelTestBuilder()
                .withId(StorageDefaultData.id)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }
}
