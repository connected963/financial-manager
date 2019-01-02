package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModelTestFactory;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.application.service.storagevalue.StorageValueByMovementService;
import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.MovementFactory;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import com.connected.accountservice.domain.querymodel.movement.SimpleMovementQueryModelTestFactory;
import com.connected.accountservice.domain.validator.movement.MovementUpdateValidator;
import com.connected.accountservice.infrastructure.repository.MovementRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MovementServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MovementService movementService;

    private MovementRepository movementRepository;

    private StorageService storageService;

    private StorageValueByMovementService storageValueByMovementService;

    @Before
    public void init() {
        this.movementRepository = Mockito.mock(MovementRepository.class);
        this.storageService = Mockito.mock(StorageService.class);
        this.storageValueByMovementService = Mockito.mock(StorageValueByMovementService.class);
        this.movementService = new MovementService(
                movementRepository, storageService, storageValueByMovementService);
    }

    @Test
    public void givenNullDocumentLinkedHash_mustFailToFindAllByDocumentLinkedHash() {
        expectedException.expect(NullPointerException.class);

        movementService.findAllByDocumentLinkedHash(null, Pageable.unpaged());

        Mockito.verifyZeroInteractions(movementRepository);
    }

    @Test
    public void givenDocumentLinkedHash_mustFindAllByDocumentLinkedHashWithSuccess() {
        Mockito.when(
                movementRepository.findAllByDocumentLinkedHash(
                        MovementDefaultData.documentLinkedHash, Pageable.unpaged()))
                .thenReturn(new PageImpl<>(List.of(
                        SimpleMovementQueryModelTestFactory.createAnDefault(),
                        SimpleMovementQueryModelTestFactory.createAnDefaultWithValue(423.38)
                )));

        final var foundMovementsPaged =
                movementService.findAllByDocumentLinkedHash(
                        MovementDefaultData.documentLinkedHash, Pageable.unpaged());
        final var foundMovements = foundMovementsPaged.getContent();


        Assert.assertThat(foundMovements, Matchers.hasSize(2));
    }

    @Test
    public void givenNullId_mustFailToFindById() {
        expectedException.expect(NullPointerException.class);

        movementService.findById(null);

        Mockito.verifyZeroInteractions(movementRepository);
    }

    @Test
    public void givenId_mustFindByIdWithSuccess() {
        final var movement = SimpleMovementQueryModelTestFactory.createAnDefault();

        Mockito.when(movementRepository.findMovementById(MovementDefaultData.id))
                .thenReturn(movement);

        final var movementFound = movementService.findById(MovementDefaultData.id);

        Assert.assertEquals(MovementDefaultData.id, movement.getId());
        Assert.assertEquals(movement, movementFound);
    }

    @Test
    public void givenNullMovementInputModel_mustFailToInsertMovement() {
        expectedException.expect(NullPointerException.class);

        movementService.insert(null);

        Mockito.verifyZeroInteractions(movementRepository);
    }

    @Test
    public void givenMovementInputModelWithoutStorage_mustInsertMovementWithSuccess() {
        final var newMovement =
                MovementInputModelTestFactory.createAnDefaultToInsertWithoutStorage();

        movementService.insert(newMovement);

        final var movementConvertedToInsert = MovementFactory.createByInputModel(newMovement);
        Mockito.verify(movementRepository).save(movementConvertedToInsert);
    }

    @Test
    public void givenMovementInputModelWithStorage_mustInsertMovementWithSuccess() {
        final var storage = StorageTestFactory.createAnDefault();
        Mockito.when(storageService.findEntityById(StorageDefaultData.id))
                .thenReturn(storage);

        final var newMovement =
                MovementInputModelTestFactory.createAnDefaultToInsert();

        movementService.insert(newMovement);

        final var movementConvertedToInsert =
                MovementFactory.createByInputModelWithStorage(newMovement, storage);
        Mockito.verify(storageValueByMovementService).insertByMovement(movementConvertedToInsert);
        Mockito.verify(movementRepository).save(movementConvertedToInsert);
    }

    @Test
    public void givenNullMovementInputModel_mustFailToUpdateMovement() {
        expectedException.expect(NullPointerException.class);

        movementService.update(null);

        Mockito.verifyZeroInteractions(movementRepository);
    }

    @Test
    public void givenMovementInputModelWithoutId_mustFailToUpdateMovement() {
        expectedException.expect(BusinessException.class);
        expectedException.expectMessage(MovementUpdateValidator.MOVEMENT_WITHOUT_ID);

        final var movementToUpdate = MovementInputModelTestFactory.createAnDefaultToInsert();

        movementService.update(movementToUpdate);

        Mockito.verifyZeroInteractions(movementRepository);
    }

    @Test
    public void givenMovementInputModelWithoutStorage_mustUpdateMovementWithSuccess() {
        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdateWithoutStorage();

        movementService.update(movementToUpdate);

        final var movementConvertedToInsert = MovementFactory.createByInputModel(movementToUpdate);
        Mockito.verify(movementRepository).save(movementConvertedToInsert);
    }

    @Test
    public void givenMovementInputModelWithStorage_mustUpdateMovementWithSuccess() {
        final var storage = StorageTestFactory.createAnDefault();
        Mockito.when(storageService.findEntityById(StorageDefaultData.id))
                .thenReturn(storage);

        final var originalMovement = MovementTestFactory.createAnDefaultToInsertWithStorage(storage);
        Mockito.when(movementRepository.getOne(MovementDefaultData.id))
                .thenReturn(originalMovement);

        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdate();

        movementService.update(movementToUpdate);

        final var movementConvertedToUpdate =
                MovementFactory.createByInputModelWithStorage(movementToUpdate, storage);
        Mockito.verify(storageValueByMovementService)
                .updateByMovement(movementConvertedToUpdate, originalMovement);
        Mockito.verify(movementRepository).save(movementConvertedToUpdate);
    }

    @Test
    public void givenNullId_mustFailToDeleteMovement() {
        expectedException.expect(NullPointerException.class);

        movementService.delete(null);

        Mockito.verifyZeroInteractions(movementRepository);
    }

    @Test
    public void givenId_mustDeleteMovementWithoutStorageWithSuccess() {
        final var movementToDelete = MovementTestFactory.createAnDefaultWithoutStorage();
        Mockito.when(movementRepository.getOne(MovementDefaultData.id))
                .thenReturn(movementToDelete);

        movementService.delete(MovementDefaultData.id);

        Mockito.verify(movementRepository).delete(movementToDelete);
        Mockito.verifyZeroInteractions(storageValueByMovementService);
    }

    @Test
    public void givenId_mustDeleteMovementHavingStorageWithSuccess() {
        final var movementToDelete = MovementTestFactory.createAnDefault();
        Mockito.when(movementRepository.getOne(MovementDefaultData.id))
                .thenReturn(movementToDelete);

        movementService.delete(MovementDefaultData.id);

        Mockito.verify(storageValueByMovementService).revertValueOfMovement(movementToDelete);
        Mockito.verify(movementRepository).delete(movementToDelete);
    }

}