package com.connected.accountservice.common.defaultdata.monetarygoal;

import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;

import java.util.UUID;

public class MonetaryGoalDefaultData {

    public static final UUID id = UUID.randomUUID();

    public static final Double value = 847.0;

    public static final Storage storage = StorageTestFactory.createAnDefault();

    private MonetaryGoalDefaultData() {

    }
}
