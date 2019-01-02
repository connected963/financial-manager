package com.connected.accountservice.domain.validator.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModel;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.validator.common.Validator;

public class MonetaryGoalUpdateValidator implements Validator<MonetaryGoalInputModel> {

    public static final String MONETARY_GOAL_WITHOUT_ID = "monetaryGoalInputModel.id.null";

    @Override
    public void validate(final MonetaryGoalInputModel monetaryGoalInputModel) {
        if (monetaryGoalInputModel.getId() == null) {
            throw new BusinessException(MONETARY_GOAL_WITHOUT_ID);
        }
    }
}
