package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModel;
import com.connected.accountservice.domain.model.storage.Storage;

public class MovementFactory {

    private MovementFactory() {

    }

    public static Movement createByInputModel(final MovementInputModel movementToUpdate) {

        return new MovementBuilder()
                .withMovementInputModel(movementToUpdate)
                .build();
    }

    public static Movement createByInputModelWithStorage(
            final MovementInputModel movementToUpdate,
            final Storage storage) {

        return new MovementBuilder()
                .withMovementInputModel(movementToUpdate)
                .withStorage(storage)
                .build();
    }
}
