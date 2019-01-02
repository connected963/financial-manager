package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModel;
import com.connected.accountservice.application.service.storagevalue.StorageValueService;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/storage")
public class StorageValueController {

    private final StorageValueService storageValueService;

    @Autowired
    public StorageValueController(final StorageValueService storageValueService) {
        this.storageValueService = storageValueService;
    }

    @GetMapping("/{storageId}/value")
    public Page<SimpleStorageValueQueryModel> findAllByStorage(
            @PathVariable final UUID storageId, final Pageable pageable) {
        return storageValueService.findAllByStorageId(storageId, pageable);
    }

    @GetMapping("/value/{storageValueId}")
    public SimpleStorageValueQueryModel findById(@PathVariable final UUID storageValueId) {
        return storageValueService.findById(storageValueId);
    }

    @PostMapping("/value")
    public void insert(@Valid @RequestBody final StorageValueInputModel storageValue) {
        storageValueService.insert(storageValue);
    }

    @PutMapping("/value")
    public void update(@Valid @RequestBody final StorageValueInputModel storageValueInputModel) {
        storageValueService.update(storageValueInputModel);
    }

    @DeleteMapping("/value/{storageValueId}")
    public void delete(@PathVariable final UUID storageValueId) {
        storageValueService.delete(storageValueId);
    }
}
