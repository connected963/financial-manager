package com.connected.accountservice.domain.model.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModelTestFactory;
import com.connected.accountservice.common.defaultdata.monetarygoal.MonetaryGoalDefaultData;
import org.junit.Assert;
import org.junit.Test;

public class MonetaryGoalBuilderTest {

    @Test
    public void givenAllData_mustCreateMonetaryGoalWithSameData() {
        final var monetaryGoalCreated = new MonetaryGoalBuilder()
                .withId(MonetaryGoalDefaultData.id)
                .withValue(MonetaryGoalDefaultData.value)
                .withStorage(MonetaryGoalDefaultData.storage)
                .build();

        final var expectedMonetaryGoal = new MonetaryGoal(
                MonetaryGoalDefaultData.id,
                MonetaryGoalDefaultData.value,
                MonetaryGoalDefaultData.storage);

        Assert.assertEquals(expectedMonetaryGoal, monetaryGoalCreated);
    }

    @Test
    public void givenStorageValueInputModel_mustCreateStorageValueWithSameData() {
        final var monetaryGoalInputModel = MonetaryGoalInputModelTestFactory.createAnDefaultToUpdate();

        final var monetaryGoalCreated = new MonetaryGoalBuilder()
                .withMonetaryGoalInputModel(monetaryGoalInputModel)
                .build();

        final var expectedMonetaryGoal = new MonetaryGoal(
                monetaryGoalInputModel.getId(),
                monetaryGoalInputModel.getValue(),
                null);

        Assert.assertEquals(expectedMonetaryGoal, monetaryGoalCreated);
    }
}