package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModel;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.application.service.storagevalue.StorageValueByMovementService;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementFactory;
import com.connected.accountservice.domain.querymodel.movement.SimpleMovementQueryModel;
import com.connected.accountservice.domain.validator.movement.MovementUpdateValidator;
import com.connected.accountservice.infrastructure.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class MovementService {

    private final MovementRepository movementRepository;

    private final StorageService storageService;

    private final StorageValueByMovementService storageValueByMovementService;

    @Autowired
    public MovementService(final MovementRepository movementRepository,
                           final StorageService storageService,
                           final StorageValueByMovementService storageValueByMovementService) {
        this.movementRepository = movementRepository;
        this.storageService = storageService;
        this.storageValueByMovementService = storageValueByMovementService;
    }

    public Page<SimpleMovementQueryModel> findAllByDocumentLinkedHash(final String documentLinkedHash,
                                                                      final Pageable pageable) {
        Objects.requireNonNull(documentLinkedHash,
                "movementService.findAllByDocumentLinkedHash.documentLinkedHash.null");

        return movementRepository.findAllByDocumentLinkedHash(documentLinkedHash, pageable);
    }

    public SimpleMovementQueryModel findById(final UUID id) {
        Objects.requireNonNull(id, "movementService.findById.id.null");

        return movementRepository.findMovementById(id);
    }

    public void insert(final MovementInputModel movementInputModel) {
        Objects.requireNonNull(movementInputModel,
                "movementService.insert.movementInputModel.null");

        saveByInputModel(movementInputModel);
    }

    private void saveByInputModel(final MovementInputModel movementInputModel) {
        if (movementInputModel.hasStorage()) {
            saveMovementWithStorage(movementInputModel);
        } else {
            saveMovement(movementInputModel);
        }
    }

    private void saveMovementWithStorage(final MovementInputModel movementInputModel) {
        final var movementToSave = createMovementWithStorage(movementInputModel);

        updateStorageValue(movementToSave);

        movementRepository.save(movementToSave);
    }

    private Movement createMovementWithStorage(final MovementInputModel movementInputModel) {
        final var storageId = movementInputModel.getStorageId();
        final var storage = storageService.findEntityById(storageId);

        return MovementFactory.createByInputModelWithStorage(movementInputModel, storage);
    }

    private void updateStorageValue(final Movement updatedMovement) {
        if (updatedMovement.isNewMovement()) {
            insertStorageValueByMovement(updatedMovement);
        } else {
            updateStorageValueByMovement(updatedMovement);
        }
    }

    private void insertStorageValueByMovement(final Movement newMovement) {
        storageValueByMovementService.insertByMovement(newMovement);
    }

    private void updateStorageValueByMovement(final Movement updatedMovement) {
        final var movementId = updatedMovement.getId();
        final var originalMovement = findEntityById(movementId);

        storageValueByMovementService.updateByMovement(updatedMovement, originalMovement);
    }

    private Movement findEntityById(final UUID id) {
        return movementRepository.getOne(id);
    }

    private void saveMovement(final MovementInputModel movementInputModel) {
        final var movementToSave = MovementFactory.createByInputModel(movementInputModel);
        movementRepository.save(movementToSave);
    }

    public void update(final MovementInputModel movementInputModel) {
        Objects.requireNonNull(movementInputModel,
                "movementService.update.movementInputModel.null");

        final var validator = new MovementUpdateValidator();
        validator.validate(movementInputModel);

        saveByInputModel(movementInputModel);
    }

    public void delete(final UUID id) {
        Objects.requireNonNull(id, "movementService.delete.id.null");

        final var movementToDelete = findEntityById(id);
        revertMovementValue(movementToDelete);
        movementRepository.delete(movementToDelete);
    }

    private void revertMovementValue(final Movement movement) {
        if (movement.hasStorage()) {
            storageValueByMovementService.revertValueOfMovement(movement);
        }
    }
}
