package com.connected.accountservice.application.inputmodel.storagevalue;

import java.time.LocalDateTime;
import java.util.UUID;

public class StorageValueInputModelTestBuilder {

    private UUID id;

    private Double value;

    private LocalDateTime date;

    private UUID storageId;

    public StorageValueInputModelTestBuilder() {

    }

    public StorageValueInputModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public StorageValueInputModelTestBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public StorageValueInputModelTestBuilder withDate(final LocalDateTime date) {
        this.date = date;
        return this;
    }

    public StorageValueInputModelTestBuilder withStorageId(final UUID storageId) {
        this.storageId = storageId;
        return this;
    }

    public StorageValueInputModel build() {
        return new StorageValueInputModel(id, value, date, storageId);
    }
}
