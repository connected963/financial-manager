package com.connected.accountservice.domain.querymodel.storagevalue;

import java.time.LocalDateTime;
import java.util.UUID;

public class SimpleStorageValueQueryModelTestBuilder {

    private UUID id;

    private Double value;

    private LocalDateTime date;

    private UUID storageId;

    public SimpleStorageValueQueryModelTestBuilder() {

    }

    public SimpleStorageValueQueryModelTestBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public SimpleStorageValueQueryModelTestBuilder withValue(final Double value) {
        this.value = value;
        return this;
    }

    public SimpleStorageValueQueryModelTestBuilder withDate(final LocalDateTime date) {
        this.date = date;
        return this;
    }

    public SimpleStorageValueQueryModelTestBuilder withStorageId(final UUID storageId) {
        this.storageId = storageId;
        return this;
    }

    public SimpleStorageValueQueryModel build() {
        return new SimpleStorageValueQueryModel(id, value, date, storageId);
    }
}
