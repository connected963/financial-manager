package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.querymodel.storage.StorageQueryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {

    Page<StorageQueryModel> findAllByDocumentLinkedHash(final String documentLinkedHash,
                                                        final Pageable pageable);

    StorageQueryModel findStorageById(final UUID id);

}
