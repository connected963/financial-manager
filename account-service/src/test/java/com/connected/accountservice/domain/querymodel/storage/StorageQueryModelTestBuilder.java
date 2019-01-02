package com.connected.accountservice.domain.querymodel.storage;

import java.util.UUID;

public class StorageQueryModelTestBuilder {

    private UUID id;

    private String name;

    public StorageQueryModelTestBuilder() {

    }

    public StorageQueryModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public StorageQueryModelTestBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public StorageQueryModel build() {
        return new StorageQueryModel(id, name);
    }
}
