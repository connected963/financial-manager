package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModel;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.domain.querymodel.movement.SimpleMovementQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/movement")
public class MovementController {

    private final MovementService movementService;

    @Autowired
    public MovementController(final MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping
    public Page<SimpleMovementQueryModel> findAll(
            @RequestParam("documentLinkedHash") final String documentLinkedHash,
            final Pageable pageable) {

        return movementService.findAllByDocumentLinkedHash(documentLinkedHash, pageable);
    }

    @GetMapping("/{movementId}")
    public SimpleMovementQueryModel findById(@PathVariable final UUID movementId) {
        return movementService.findById(movementId);
    }

    @PostMapping
    public void insert(@Valid @RequestBody final MovementInputModel movement) {
        movementService.insert(movement);
    }

    @PutMapping
    public void update(@Valid @RequestBody final MovementInputModel movement) {
        movementService.update(movement);
    }

    @DeleteMapping("/{movementId}")
    public void delete(@PathVariable final UUID movementId) {
        movementService.delete(movementId);
    }
}
