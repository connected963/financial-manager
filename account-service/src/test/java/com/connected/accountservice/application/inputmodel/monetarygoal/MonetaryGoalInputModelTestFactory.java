package com.connected.accountservice.application.inputmodel.monetarygoal;

import com.connected.accountservice.common.defaultdata.monetarygoal.MonetaryGoalDefaultData;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;

public final class MonetaryGoalInputModelTestFactory {

    private MonetaryGoalInputModelTestFactory() {

    }

    public static MonetaryGoalInputModel createAnDefaultToInsert() {
        return new MonetaryGoalInputModelTestBuilder()
                .withValue(MonetaryGoalDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static MonetaryGoalInputModel createAnDefaultToInsertWithoutValue() {
        return new MonetaryGoalInputModelTestBuilder()
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static MonetaryGoalInputModel createAnDefaultToInsertWithoutStorage() {
        return new MonetaryGoalInputModelTestBuilder()
                .withValue(MonetaryGoalDefaultData.value)
                .build();
    }

    public static MonetaryGoalInputModel createAnDefaultToUpdate() {
        return new MonetaryGoalInputModelTestBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withValue(MonetaryGoalDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static MonetaryGoalInputModel createAnDefaultToUpdateWithoutValue() {
        return new MonetaryGoalInputModelTestBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withStorageId(StorageDefaultData.id)
                .build();
    }

    public static MonetaryGoalInputModel createAnDefaultToUpdateWithoutStorage() {
        return new MonetaryGoalInputModelTestBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withValue(MonetaryGoalDefaultData.value)
                .build();
    }
}
