package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModelTestFactory;
import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import org.junit.Assert;
import org.junit.Test;

public class MovementBuilderTest {

    @Test
    public void givenAllData_mustCreateMovementWithSameData() {
        final var movement = new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withType(MovementDefaultData.type)
                .withValue(MovementDefaultData.value)
                .withStorage(MovementDefaultData.storage)
                .withRepeatMonthly(MovementDefaultData.repeatMonthly)
                .withDocumentLinkedHash(MovementDefaultData.documentLinkedHash)
                .build();

        final var expectedMovement = new Movement(
                MovementDefaultData.id,
                MovementDefaultData.type,
                MovementDefaultData.value,
                MovementDefaultData.storage,
                MovementDefaultData.repeatMonthly,
                MovementDefaultData.documentLinkedHash);

        Assert.assertEquals(expectedMovement, movement);
    }

    @Test
    public void givenMovementInputModel_mustCreateMovementWithSameData() {
        final var movementInputModel = MovementInputModelTestFactory.createAnDefaultToUpdate();
        final var storage = StorageTestFactory.createAnDefault();

        final var movementCreated = new MovementBuilder()
                .withMovementInputModel(movementInputModel)
                .withStorage(storage)
                .build();

        final var expectedMovement = new Movement(
                movementInputModel.getId(),
                movementInputModel.getType(),
                movementInputModel.getValue(),
                storage,
                movementInputModel.getRepeatMonthly(),
                movementInputModel.getDocumentLinkedHash());

        Assert.assertEquals(expectedMovement, movementCreated);
    }
}