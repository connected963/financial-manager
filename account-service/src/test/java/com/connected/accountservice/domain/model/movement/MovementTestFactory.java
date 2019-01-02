package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.domain.common.enums.MovementType;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;

public final class MovementTestFactory {

    private MovementTestFactory() {

    }

    public static Movement createAnDefault() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorage(StorageTestFactory.createAnDefault())
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnInputWithValue(final Double value) {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementType.INPUT)
                .withValue(value)
                .withStorage(StorageTestFactory.createAnDefault())
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnInputWithValueAndStorage(final Double value,
                                                            final Storage storage) {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementType.INPUT)
                .withValue(value)
                .withStorage(storage)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnOutputWithValue(final Double value) {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementType.OUTPUT)
                .withValue(value)
                .withStorage(StorageTestFactory.createAnDefault())
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnDefaultWithoutStorage() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnDefaultToInsert() {
        return new MovementBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorage(MovementDefaultData.storage)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnDefaultToInsertWithStorage(final Storage storage) {
        return new MovementBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorage(storage)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static Movement createAnDefaultToInsertWithStorageAndDocumentLinkedHash(
            final Storage storage, final String documentLinkedHash) {
        return new MovementBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorage(storage)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(documentLinkedHash)
                .build();
    }

}
