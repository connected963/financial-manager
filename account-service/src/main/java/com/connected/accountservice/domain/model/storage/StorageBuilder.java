package com.connected.accountservice.domain.model.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModel;

import java.util.UUID;

class StorageBuilder {

    private UUID id;

    private String name;

    private String documentLinkedHash;

    StorageBuilder() {

    }

    StorageBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    StorageBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    StorageBuilder withDocumentLinkedHash(final String documentLinkedHash) {
        this.documentLinkedHash = documentLinkedHash;
        return this;
    }

    StorageBuilder withStorageInputModel(final StorageInputModel storageInputModel) {
        this.id = storageInputModel.getId();
        this.name = storageInputModel.getName();
        this.documentLinkedHash = storageInputModel.getDocumentLinkedHash();
        return this;
    }

    Storage build() {
        return new Storage(id, name, documentLinkedHash);
    }
}
