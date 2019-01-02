package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModel;
import com.connected.accountservice.application.service.monetarygoal.MonetaryGoalService;
import com.connected.accountservice.domain.querymodel.monetarygoal.SimpleMonetaryGoalQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/monetarygoal")
public class MonetaryGoalController {

    private final MonetaryGoalService monetaryGoalService;

    @Autowired
    public MonetaryGoalController(MonetaryGoalService monetaryGoalService) {
        this.monetaryGoalService = monetaryGoalService;
    }

    @GetMapping
    public Page<SimpleMonetaryGoalQueryModel> findAll(
            @RequestParam("documentLinkedHash") final String documentLinkedHash,
            final Pageable pageable) {

        return monetaryGoalService.findAllByDocumentLinkedHash(documentLinkedHash, pageable);
    }

    @GetMapping("/{monetaryGoalId}")
    public SimpleMonetaryGoalQueryModel buscarPorId(@PathVariable final UUID monetaryGoalId) {
        return monetaryGoalService.findById(monetaryGoalId);
    }

    @PostMapping
    public void insert(@Valid @RequestBody final MonetaryGoalInputModel monetaryGoal) {
        monetaryGoalService.insert(monetaryGoal);
    }

    @PutMapping
    public void update(@Valid @RequestBody final MonetaryGoalInputModel monetaryGoal) {
        monetaryGoalService.update(monetaryGoal);
    }

    @DeleteMapping("/{monetaryGoalId}")
    public void delete(@PathVariable final UUID monetaryGoalId) {
        monetaryGoalService.delete(monetaryGoalId);
    }
}
