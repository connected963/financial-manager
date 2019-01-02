package com.connected.accountservice.domain.model.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModelTestFactory;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import org.junit.Assert;
import org.junit.Test;

public class MonetaryGoalFactoryTest {

    @Test
    public void givenInputModelAndStorage_mustCreateStorageWithSameData() {
        final var monetaryGoalInputModel = MonetaryGoalInputModelTestFactory.createAnDefaultToUpdate();
        final var storage = StorageTestFactory.createAnDefault();

        final var monetaryGoalCreated =
                MonetaryGoalFactory.createByInputModelWithStorage(monetaryGoalInputModel, storage);

        final var expectedMonetaryGoal = new MonetaryGoalBuilder()
                .withMonetaryGoalInputModel(monetaryGoalInputModel)
                .withStorage(storage)
                .build();

        Assert.assertEquals(expectedMonetaryGoal, monetaryGoalCreated);
    }

}