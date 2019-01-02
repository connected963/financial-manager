package com.connected.accountservice.domain.model.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModel;
import com.connected.accountservice.domain.model.storage.Storage;

import java.time.LocalDateTime;
import java.util.UUID;

class StorageValueBuilder {

    private static final Double DEFAULT_VALUE = 0.0;

    private UUID id;

    private Double value;

    private LocalDateTime date;

    private Storage storage;

    StorageValueBuilder() {
        this.value = DEFAULT_VALUE;
    }

    StorageValueBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    StorageValueBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    StorageValueBuilder withDate(final LocalDateTime date) {
        this.date = date;
        return this;
    }

    StorageValueBuilder withStorage(final Storage storage) {
        this.storage = storage;
        return this;
    }

    StorageValueBuilder withStorageValueInputModel(
            final StorageValueInputModel storageValueInputModel) {
        this.id = storageValueInputModel.getId();
        this.value = storageValueInputModel.getValue();
        this.date = storageValueInputModel.getDate();
        return this;
    }

    StorageValue build() {
        return new StorageValue(id, value, date, storage);
    }
}
