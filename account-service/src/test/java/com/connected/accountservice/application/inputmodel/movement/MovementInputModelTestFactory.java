package com.connected.accountservice.application.inputmodel.movement;

import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;

public final class MovementInputModelTestFactory {

    private MovementInputModelTestFactory() {

    }

    public static MovementInputModel createAnDefaultToInsert() {
        return new MovementInputModelTestBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToInsertWithoutStorage() {
        return new MovementInputModelTestBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToUpdate() {
        return new MovementInputModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToUpdateWithoutStorage() {
        return new MovementInputModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }
}
