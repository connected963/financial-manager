package com.connected.accountservice.application.service.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModel;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageFactory;
import com.connected.accountservice.domain.querymodel.storage.StorageQueryModel;
import com.connected.accountservice.domain.validator.storage.StorageUpdateValidator;
import com.connected.accountservice.infrastructure.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    @Autowired
    public StorageService(final StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Page<StorageQueryModel> findAllByDocumentLinkedHash(final String documentLinkedHash,
                                                               final Pageable pageable) {
        Objects.requireNonNull(documentLinkedHash,
                "storageService.findAllByDocumentLinkedHash.documentLinkedHash.null");
        return storageRepository.findAllByDocumentLinkedHash(documentLinkedHash, pageable);
    }

    public StorageQueryModel findById(final UUID id) {
        Objects.requireNonNull(id, "storageService.findById.id.null");

        return storageRepository.findStorageById(id);
    }

    public Storage findEntityById(final UUID id) {
        Objects.requireNonNull(id, "storageService.findEntityById.id.null");

        return storageRepository.getOne(id);
    }

    public void insert(final StorageInputModel storageInputModel) {
        Objects.requireNonNull(storageInputModel,
                "storageService.insert.storageInputModel.null");

        saveByStorageInputModel(storageInputModel);
    }

    private void saveByStorageInputModel(final StorageInputModel storageInputModel) {
        final var storageToInsert = StorageFactory.createByInputModel(storageInputModel);
        storageRepository.save(storageToInsert);
    }

    public void update(final StorageInputModel storageInputModel) {
        Objects.requireNonNull(storageInputModel,
                "storageService.update.storageInputModel.null");

        final var validator = new StorageUpdateValidator();
        validator.validate(storageInputModel);

        saveByStorageInputModel(storageInputModel);
    }

    public void delete(final UUID id) {
        Objects.requireNonNull(id, "storageService.delete.id.null");

        storageRepository.deleteById(id);
    }
}
