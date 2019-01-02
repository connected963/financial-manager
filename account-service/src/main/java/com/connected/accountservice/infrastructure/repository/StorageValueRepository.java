package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storagevalue.StorageValue;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageValueRepository extends JpaRepository<StorageValue, UUID> {

    @Query("SELECT new com.connected.accountservice.domain.querymodel.storagevalue." +
            "SimpleStorageValueQueryModel(" +
            "      storageValue.id, " +
            "      storageValue.value, " +
            "      storageValue.date, " +
            "      storageValue.storage.id) " +
            "FROM StorageValue storageValue " +
            "WHERE storageValue.storage.id = :storageId")
    Page<SimpleStorageValueQueryModel> findAllByStorageId(final UUID storageId,
                                                          final Pageable pageable);

    Optional<StorageValue> findFirstByStorageEqualsOrderByDateDesc(final Storage storage);

    @Query("SELECT new com.connected.accountservice.domain.querymodel.storagevalue." +
            "SimpleStorageValueQueryModel(" +
            "      storageValue.id, " +
            "      storageValue.value, " +
            "      storageValue.date, " +
            "      storageValue.storage.id) " +
            "FROM StorageValue storageValue " +
            "WHERE storageValue.id = :id")
    SimpleStorageValueQueryModel findStorageValueById(final UUID id);

}