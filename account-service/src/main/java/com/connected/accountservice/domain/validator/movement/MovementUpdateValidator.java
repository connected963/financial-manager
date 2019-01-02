package com.connected.accountservice.domain.validator.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModel;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.validator.common.Validator;

public class MovementUpdateValidator implements Validator<MovementInputModel> {

    public static final String MOVEMENT_WITHOUT_ID = "movementInputModel.id.null";

    @Override
    public void validate(final MovementInputModel movementInputModel) {
        if (movementInputModel.getId() == null) {
            throw new BusinessException(MOVEMENT_WITHOUT_ID);
        }
    }
}
