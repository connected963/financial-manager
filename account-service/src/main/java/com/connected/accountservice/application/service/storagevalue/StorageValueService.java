package com.connected.accountservice.application.service.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModel;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storagevalue.StorageValue;
import com.connected.accountservice.domain.model.storagevalue.StorageValueFactory;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModel;
import com.connected.accountservice.domain.validator.storagevalue.StorageValueUpdateValidator;
import com.connected.accountservice.infrastructure.repository.StorageValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class StorageValueService {

    private final StorageValueRepository storageValueRepository;

    private final StorageService storageService;

    @Autowired
    public StorageValueService(final StorageValueRepository storageValueRepository,
                               final StorageService storageService) {
        this.storageValueRepository = storageValueRepository;
        this.storageService = storageService;
    }

    public Page<SimpleStorageValueQueryModel> findAllByStorageId(final UUID storageId,
                                                                 final Pageable pageable) {
        Objects.requireNonNull(storageId,
                "storageValueService.findAllByStorageId.storageId.null");
        return storageValueRepository.findAllByStorageId(storageId, pageable);
    }

    public SimpleStorageValueQueryModel findById(final UUID id) {
        Objects.requireNonNull(id, "storageValueService.findById.id.null");

        return storageValueRepository.findStorageValueById(id);
    }

    public StorageValue insert(final StorageValueInputModel storageValueInputModel) {
        Objects.requireNonNull(storageValueInputModel,
                "storageValueService.insert.storageValueInputModel.null");

        return saveByStorageValueInputModel(storageValueInputModel);
    }

    private StorageValue saveByStorageValueInputModel(final StorageValueInputModel storageValueInputModel) {
        final var storageValue =
                createStorageValueByStorageValueInputModel(storageValueInputModel);
        return storageValueRepository.save(storageValue);
    }

    private StorageValue createStorageValueByStorageValueInputModel(
            final StorageValueInputModel storageValueInputModel) {

        final var storage = findStorageByStorageValueInputModel(storageValueInputModel);
        return StorageValueFactory.createByInputModelWithStorage(storageValueInputModel, storage);
    }

    private Storage findStorageByStorageValueInputModel(
            final StorageValueInputModel storageValueInputModel) {
        final var storageId = storageValueInputModel.getStorageId();
        return storageService.findEntityById(storageId);
    }

    public void update(final StorageValueInputModel storageValueUpdateInputModel) {
        Objects.requireNonNull(storageValueUpdateInputModel,
                "storageValueService.update.storageValueUpdateInputModel.null");
        final var validator = new StorageValueUpdateValidator();
        validator.validate(storageValueUpdateInputModel);

        saveByStorageValueInputModel(storageValueUpdateInputModel);
    }

    public void delete(final UUID id) {
        Objects.requireNonNull(id, "storageValueService.delete.id.null");

        storageValueRepository.deleteById(id);
    }
}