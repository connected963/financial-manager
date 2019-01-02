package com.connected.accountservice.application.service.storagevalue;

import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import com.connected.accountservice.domain.model.storagevalue.StorageValueTestFactory;
import com.connected.accountservice.infrastructure.repository.StorageValueRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StorageValueByMovementServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private StorageValueRepository storageValueRepository;

    private StorageValueByMovementService storageValueByMovementService;

    @Before
    public void init() {
        this.storageValueRepository = Mockito.mock(StorageValueRepository.class);
        this.storageValueByMovementService = new StorageValueByMovementService(storageValueRepository);
    }

    @Test
    public void givenNullMovement_mustFailToInsertByMovement() {
        expectedException.expect(NullPointerException.class);

        storageValueByMovementService.insertByMovement(null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenMovementWithStorageWithoutValue_mustCreateValueWithMovementValue() {
        final var movement = MovementTestFactory.createAnInputWithValue(354.54);

        storageValueByMovementService.insertByMovement(movement);

        final var newStorageValue = StorageValueTestFactory.createByMovement(movement);

        Mockito.verify(storageValueRepository).save(newStorageValue);
    }

    @Test
    public void givenMovementWithStorageWithValue_mustCreateValueWithRecalculatedValue() {
        final var lastStorageValue = StorageValueTestFactory.createAnDefaultToInsertWithValue(450.0);
        Mockito.when(storageValueRepository
                .findFirstByStorageEqualsOrderByDateDesc(StorageValueDefaultData.storage))
                .thenReturn(Optional.of(lastStorageValue));

        final var movement = MovementTestFactory.createAnInputWithValue(354.54);

        storageValueByMovementService.insertByMovement(movement);

        final var newStorageValue = lastStorageValue.recalculateAddingMovement(movement);

        Mockito.verify(storageValueRepository).save(newStorageValue);
    }

    @Test
    public void givenNullUpdatedMovement_mustFailToUpdateByMovement() {
        expectedException.expect(NullPointerException.class);

        final var originalMovement = MovementTestFactory.createAnDefault();
        storageValueByMovementService.updateByMovement(null, originalMovement);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenNullOriginalMovement_mustFailToUpdateByMovement() {
        expectedException.expect(NullPointerException.class);

        final var updatedMovement = MovementTestFactory.createAnDefault();
        storageValueByMovementService.updateByMovement(updatedMovement, null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void givenUpdatedAndOriginalMovementWithSameStorage_mustCreateValueRecalculated() {
        final var lastStorageValue = StorageValueTestFactory.createAnDefaultToInsertWithValue(456.4);

        final var updatedMovement = MovementTestFactory.createAnInputWithValue(245.25);
        final var originalMovement = MovementTestFactory.createAnOutputWithValue(350.4);

        final var storageValueWithOriginalMovementReverted =
                lastStorageValue.recalculateRemovingMovement(originalMovement);
        final var storageValueWithUpdatedValue =
                storageValueWithOriginalMovementReverted.recalculateAddingMovement(updatedMovement);

        Mockito.when(storageValueRepository
                .findFirstByStorageEqualsOrderByDateDesc(MovementDefaultData.storage))
                .thenReturn(Optional.of(lastStorageValue),
                        Optional.of(storageValueWithOriginalMovementReverted));

        storageValueByMovementService.updateByMovement(updatedMovement, originalMovement);

        Mockito.verify(storageValueRepository).save(storageValueWithUpdatedValue);
    }

    @Test
    public void givenUpdatedAndOriginalMovementWithDivergentStorage_mustRevertOriginalMovementOnStorageAndCreateNewValueForNewStorage() {
        final var lastStorageValueOriginalMovement =
                StorageValueTestFactory.createAnDefaultToInsertWithValue(468.8);
        Mockito.when(storageValueRepository
                .findFirstByStorageEqualsOrderByDateDesc(MovementDefaultData.storage))
                .thenReturn(Optional.of(lastStorageValueOriginalMovement));

        final var storageUpdatedMovement = StorageTestFactory.createAnDefaultWithName("updated storage");
        final var lastStorageValueUpdatedMovement =
                StorageValueTestFactory.createAnDefaultToInsertWithStorage(storageUpdatedMovement);
        Mockito.when(storageValueRepository
                .findFirstByStorageEqualsOrderByDateDesc(storageUpdatedMovement))
                .thenReturn(Optional.of(lastStorageValueUpdatedMovement));

        final var updatedMovement = MovementTestFactory.createAnInputWithValueAndStorage(
                245.25, storageUpdatedMovement);
        final var originalMovement = MovementTestFactory.createAnOutputWithValue(350.4);

        final var storageValueWithOriginalMovementReverted =
                lastStorageValueOriginalMovement.recalculateRemovingMovement(originalMovement);
        final var storageValueWithUpdatedValue =
                lastStorageValueUpdatedMovement.recalculateAddingMovement(updatedMovement);

        storageValueByMovementService.updateByMovement(updatedMovement, originalMovement);

        Mockito.verify(storageValueRepository).save(storageValueWithOriginalMovementReverted);
        Mockito.verify(storageValueRepository).save(storageValueWithUpdatedValue);
    }

    @Test
    public void givenNullMovement_mustFailToRevertValueOfMovement() {
        expectedException.expect(NullPointerException.class);

        storageValueByMovementService.revertValueOfMovement(null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenMovement_mustRevertValueOfMovement() {
        final var lastStorageValue =
                StorageValueTestFactory.createAnDefaultToInsertWithValue(978.4);
        Mockito.when(storageValueRepository
                .findFirstByStorageEqualsOrderByDateDesc(MovementDefaultData.storage))
                .thenReturn(Optional.of(lastStorageValue));

        final var movement = MovementTestFactory.createAnOutputWithValue(350.4);

        final var storageValueWithMovementReverted =
                lastStorageValue.recalculateRemovingMovement(movement);

        storageValueByMovementService.revertValueOfMovement(movement);

        Mockito.verify(storageValueRepository).save(storageValueWithMovementReverted);
    }

}