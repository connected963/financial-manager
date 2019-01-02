package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.querymodel.movement.SimpleMovementQueryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {

    @Query("SELECT new com.connected.accountservice.domain.querymodel.movement." +
            "SimpleMovementQueryModel(" +
            "      movement.id, " +
            "      movement.type, " +
            "      movement.value, " +
            "      movement.storage.id, " +
            "      movement.repeatMonthly) " +
            "FROM Movement movement " +
            "WHERE movement.documentLinkedHash = :documentLinkedHash")
    Page<SimpleMovementQueryModel> findAllByDocumentLinkedHash(final String documentLinkedHash,
                                                               final Pageable pageable);

    @Query("SELECT new com.connected.accountservice.domain.querymodel.movement." +
            "SimpleMovementQueryModel(" +
            "      movement.id, " +
            "      movement.type, " +
            "      movement.value, " +
            "      movement.storage.id, " +
            "      movement.repeatMonthly) " +
            "FROM Movement movement " +
            "WHERE movement.id = :id")
    SimpleMovementQueryModel findMovementById(final UUID id);

}
