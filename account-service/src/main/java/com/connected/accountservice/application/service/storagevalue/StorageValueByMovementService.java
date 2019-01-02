package com.connected.accountservice.application.service.storagevalue;

import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storagevalue.StorageValue;
import com.connected.accountservice.domain.model.storagevalue.StorageValueFactory;
import com.connected.accountservice.infrastructure.repository.StorageValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class StorageValueByMovementService {

    private final StorageValueRepository storageValueRepository;

    @Autowired
    public StorageValueByMovementService(final StorageValueRepository storageValueRepository) {
        this.storageValueRepository = storageValueRepository;
    }

    public void insertByMovement(final Movement newMovement) {
        Objects.requireNonNull(newMovement,
                "storageValueByMovementService.insertByMovement.newMovement.null");

        final var lastStorageValue = findLastStorageValueByMovement(newMovement);
        final var updatedValue = lastStorageValue.recalculateAddingMovement(newMovement);

        storageValueRepository.save(updatedValue);
    }

    private StorageValue findLastStorageValueByMovement(final Movement movement) {
        final var movementStorage = movement.getStorage();

        return findLastStorageValue(movementStorage);
    }

    private StorageValue findLastStorageValue(final Storage storage) {
        final Supplier<StorageValue> createEmptyStorageValue =
                () -> StorageValueFactory.createNew(storage);

        final Optional<StorageValue> lastStorageValue =
                storageValueRepository.findFirstByStorageEqualsOrderByDateDesc(storage);
        return lastStorageValue.orElseGet(createEmptyStorageValue);
    }

    public void updateByMovement(final Movement updatedMovement, final Movement originalMovement) {
        Objects.requireNonNull(updatedMovement,
                "storageValueByMovementService.updateByMovement.updatedMovement.null");
        Objects.requireNonNull(originalMovement,
                "storageValueByMovementService.updateByMovement.originalMovement.null");

        revertValueOfMovement(originalMovement);
        insertByMovement(updatedMovement);
    }

    public void revertValueOfMovement(final Movement movement) {
        Objects.requireNonNull(movement,
                "storageValueByMovementService.revertValueOfMovement.movement.null");

        final var lastStorageValue = findLastStorageValueByMovement(movement);
        final var updatedValue = lastStorageValue.recalculateRemovingMovement(movement);

        storageValueRepository.save(updatedValue);
    }
}
