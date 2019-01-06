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

    public static MovementInputModel createAnDefaultToInsertWithoutType() {
        return new MovementInputModelTestBuilder()
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToInsertWithoutValue() {
        return new MovementInputModelTestBuilder()
                .withType(MovementDefaultData.type)
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

    public static MovementInputModel createAnDefaultToInsertWithoutRepeatMonthly() {
        return new MovementInputModelTestBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToInsertWithoutDocumentLinkedHash() {
        return new MovementInputModelTestBuilder()
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
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

    public static MovementInputModel createAnDefaultToUpdateWithoutType() {
        return new MovementInputModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToUpdateWithoutValue() {
        return new MovementInputModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
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

    public static MovementInputModel createAnDefaultToUpdateWithoutRepeatMonthly() {
        return new MovementInputModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();
    }

    public static MovementInputModel createAnDefaultToUpdateWithoutDocumentLinkedHash() {
        return new MovementInputModelTestBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .build();
    }
}
