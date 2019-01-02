package com.connected.accountservice.application.service.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModel;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.domain.model.monetarygoal.MonetaryGoal;
import com.connected.accountservice.domain.model.monetarygoal.MonetaryGoalFactory;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.querymodel.monetarygoal.SimpleMonetaryGoalQueryModel;
import com.connected.accountservice.domain.validator.monetarygoal.MonetaryGoalUpdateValidator;
import com.connected.accountservice.infrastructure.repository.MonetaryGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class MonetaryGoalService {

    private final MonetaryGoalRepository monetaryGoalRepository;

    private final StorageService storageService;

    @Autowired
    public MonetaryGoalService(final MonetaryGoalRepository monetaryGoalRepository,
                               final StorageService storageService) {
        this.monetaryGoalRepository = monetaryGoalRepository;
        this.storageService = storageService;
    }

    public Page<SimpleMonetaryGoalQueryModel> findAllByDocumentLinkedHash(final String documentLinkedHash,
                                                                          final Pageable pageable) {
        Objects.requireNonNull(documentLinkedHash,
                "monetaryGoalService.findAllByDocumentLinkedHash.documentLinkedHash.null");

        return monetaryGoalRepository.findAllByDocumentLinkedHash(documentLinkedHash, pageable);
    }

    public SimpleMonetaryGoalQueryModel findById(final UUID id) {
        Objects.requireNonNull(id, "monetaryGoalService.findById.id.null");

        return monetaryGoalRepository.findMonetaryGoalById(id);
    }

    public void insert(final MonetaryGoalInputModel monetaryGoalInputModel) {
        Objects.requireNonNull(monetaryGoalInputModel,
                "monetaryGoalService.insert.monetaryGoalInputModel.null");

        saveByMonetaryGoalInputModel(monetaryGoalInputModel);
    }

    private void saveByMonetaryGoalInputModel(final MonetaryGoalInputModel monetaryGoalInputModel) {
        final var monetaryGoal = createMonetaryGoalByInputModel(monetaryGoalInputModel);
        monetaryGoalRepository.save(monetaryGoal);
    }

    private MonetaryGoal createMonetaryGoalByInputModel(
            final MonetaryGoalInputModel monetaryGoalInputModel) {

        final var storage = findStorageByMonetaryGoalInputModel(monetaryGoalInputModel);
        return MonetaryGoalFactory.createByInputModelWithStorage(monetaryGoalInputModel, storage);

    }

    private Storage findStorageByMonetaryGoalInputModel(
            final MonetaryGoalInputModel monetaryGoalInputModel) {
        final var storageId = monetaryGoalInputModel.getStorageId();

        return storageService.findEntityById(storageId);
    }

    public void update(final MonetaryGoalInputModel monetaryGoalInputModel) {
        Objects.requireNonNull(monetaryGoalInputModel,
                "monetaryGoalService.update.monetaryGoalInputModel.null");

        final var validator = new MonetaryGoalUpdateValidator();
        validator.validate(monetaryGoalInputModel);

        saveByMonetaryGoalInputModel(monetaryGoalInputModel);
    }

    public void delete(final UUID id) {
        Objects.requireNonNull(id, "monetaryGoalService.delete.id.null");

        monetaryGoalRepository.deleteById(id);
    }
}
