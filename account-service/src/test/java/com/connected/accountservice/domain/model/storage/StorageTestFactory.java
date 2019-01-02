package com.connected.accountservice.domain.model.storage;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;

public final class StorageTestFactory {

    private StorageTestFactory() {

    }

    public static Storage createAnDefault() {
        return new StorageBuilder()
                .withId(StorageDefaultData.id)
                .withName(StorageDefaultData.name)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static Storage createAnDefaultWithName(final String name) {
        return new StorageBuilder()
                .withId(StorageDefaultData.id)
                .withName(name)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static Storage createAnDefaultToInsert() {
        return new StorageBuilder()
                .withName(StorageDefaultData.name)
                .withDocumentLinkedHash(StorageDefaultData.documentLinkedHash)
                .build();
    }

    public static Storage createAnDefaultToInsertWithDocumentLinkedHash(
            final String documentLinkedHash) {
        return new StorageBuilder()
                .withName(StorageDefaultData.name)
                .withDocumentLinkedHash(documentLinkedHash)
                .build();
    }

}
