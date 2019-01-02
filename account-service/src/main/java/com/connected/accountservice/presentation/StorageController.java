package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModel;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.domain.querymodel.storage.StorageQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public Page<StorageQueryModel> findAll(
            @RequestParam("documentLinkedHash") final String documentLinkedHash,
            final Pageable pageable) {

        return storageService.findAllByDocumentLinkedHash(documentLinkedHash, pageable);
    }

    @GetMapping("/{storageId}")
    public StorageQueryModel findById(@PathVariable final UUID storageId) {
        return storageService.findById(storageId);
    }

    @PostMapping
    public void insert(@Valid @RequestBody final StorageInputModel storage) {
        storageService.insert(storage);
    }

    @PutMapping
    public void update(@Valid @RequestBody final StorageInputModel storage) {
        storageService.update(storage);
    }

    @DeleteMapping("/{storageId}")
    public void delete(@PathVariable final UUID storageId) {
        storageService.delete(storageId);
    }
}
