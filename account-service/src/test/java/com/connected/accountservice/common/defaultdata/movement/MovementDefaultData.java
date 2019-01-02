package com.connected.accountservice.common.defaultdata.movement;

import com.connected.accountservice.domain.common.enums.MovementType;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;

import java.util.UUID;

public class MovementDefaultData {

    public static final UUID id = UUID.randomUUID();

    public static final MovementType type = MovementType.INPUT;

    public static final Double value = 20.0;

    public static final Storage storage = StorageTestFactory.createAnDefault();

    public static final Boolean repeatMonthly = false;

    public static final String documentLinkedHash = "CPF12395478357";

    private MovementDefaultData() {

    }
}
