package com.connected.accountservice.domain.querymodel.monetarygoal;

import com.connected.accountservice.common.defaultdata.monetarygoal.MonetaryGoalDefaultData;

public final class SimpleMonetaryGoalQueryModelTestFactory {

    private SimpleMonetaryGoalQueryModelTestFactory() {

    }

    public static SimpleMonetaryGoalQueryModel createAnDefault() {
        return new SimpleMonetaryGoalQueryModelTestBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withValue(MonetaryGoalDefaultData.value)
                .withStorageId(MonetaryGoalDefaultData.id)
                .build();
    }

    public static SimpleMonetaryGoalQueryModel createAnDefaultWithValue(final Double value) {
        return new SimpleMonetaryGoalQueryModelTestBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withValue(value)
                .withStorageId(MonetaryGoalDefaultData.id)
                .build();
    }
}
