package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.domain.model.monetarygoal.MonetaryGoal;
import com.connected.accountservice.domain.querymodel.monetarygoal.SimpleMonetaryGoalQueryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonetaryGoalRepository extends JpaRepository<MonetaryGoal, UUID> {

    @Query("SELECT new com.connected.accountservice.domain.querymodel.monetarygoal." +
            "SimpleMonetaryGoalQueryModel(monetaryGoal.id, " +
            "      monetaryGoal.value, " +
            "      monetaryGoal.storage.id) " +
            "FROM MonetaryGoal monetaryGoal " +
            "WHERE monetaryGoal.storage.documentLinkedHash = :documentLinkedHash")
    Page<SimpleMonetaryGoalQueryModel> findAllByDocumentLinkedHash(
            final String documentLinkedHash, final Pageable pageable);

    @Query("SELECT new com.connected.accountservice.domain.querymodel.monetarygoal." +
            "SimpleMonetaryGoalQueryModel(" +
            "      monetaryGoal.id, " +
            "      monetaryGoal.value, " +
            "      monetaryGoal.storage.id) " +
            "FROM MonetaryGoal monetaryGoal " +
            "WHERE monetaryGoal.id = :id")
    SimpleMonetaryGoalQueryModel findMonetaryGoalById(final UUID id);

}
