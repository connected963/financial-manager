package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModelTestFactory;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import org.junit.Assert;
import org.junit.Test;

public class MovementFactoryTest {

    @Test
    public void givenInputModel_mustCreateStorageWithSameData() {
        final var movementInputModel = MovementInputModelTestFactory.createAnDefaultToUpdate();

        final var movementCreated = MovementFactory.createByInputModel(movementInputModel);

        final var expectedMovement = new MovementBuilder()
                .withMovementInputModel(movementInputModel)
                .build();

        Assert.assertEquals(expectedMovement, movementCreated);
    }

    @Test
    public void givenInputModelAndStorage_mustCreateStorageWithSameData() {
        final var movementInputModel = MovementInputModelTestFactory.createAnDefaultToUpdate();
        final var storage = StorageTestFactory.createAnDefault();

        final var movementCreated =
                MovementFactory.createByInputModelWithStorage(movementInputModel, storage);

        final var expectedMovement = new MovementBuilder()
                .withMovementInputModel(movementInputModel)
                .withStorage(storage)
                .build();

        Assert.assertEquals(expectedMovement, movementCreated);
    }

}