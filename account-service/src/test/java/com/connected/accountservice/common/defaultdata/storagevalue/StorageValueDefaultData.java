package com.connected.accountservice.common.defaultdata.storagevalue;

import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public final class StorageValueDefaultData {

    public static final UUID id = UUID.randomUUID();

    public static final Double value = 923.5;

    public static final LocalDateTime date = LocalDateTime.now();

    public static final Storage storage = StorageTestFactory.createAnDefault();

    private StorageValueDefaultData() {

    }

}
