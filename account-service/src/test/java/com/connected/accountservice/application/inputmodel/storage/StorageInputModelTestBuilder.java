package com.connected.accountservice.application.inputmodel.storage;

import java.util.UUID;

public class StorageInputModelTestBuilder {

    private UUID id;

    private String name;

    private String documentLinkedHash;

    public StorageInputModelTestBuilder() {

    }

    public StorageInputModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public StorageInputModelTestBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public StorageInputModelTestBuilder withDocumentLinkedHash(final String documentLinkedHash) {
        this.documentLinkedHash = documentLinkedHash;
        return this;
    }

    public StorageInputModel build() {
        return new StorageInputModel(id, name, documentLinkedHash);
    }
}
