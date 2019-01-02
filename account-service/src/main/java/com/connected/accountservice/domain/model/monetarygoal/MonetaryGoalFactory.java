package com.connected.accountservice.domain.model.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModel;
import com.connected.accountservice.domain.model.storage.Storage;

public class MonetaryGoalFactory {

    private MonetaryGoalFactory() {

    }

    public static MonetaryGoal createByInputModelWithStorage(
            final MonetaryGoalInputModel monetaryGoalToUpdate,
            final Storage storage) {

        return new MonetaryGoalBuilder()
                .withMonetaryGoalInputModel(monetaryGoalToUpdate)
                .withStorage(storage)
                .build();
    }
}
