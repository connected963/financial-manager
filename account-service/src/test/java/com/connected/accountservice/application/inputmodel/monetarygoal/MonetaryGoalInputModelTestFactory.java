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

    public static MonetaryGoalInputModel createAnDefaultToUpdate() {
        return new MonetaryGoalInputModelTestBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withValue(MonetaryGoalDefaultData.value)
                .withStorageId(StorageDefaultData.id)
                .build();
    }
}
