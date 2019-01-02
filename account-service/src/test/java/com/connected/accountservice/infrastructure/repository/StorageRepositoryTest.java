package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StorageRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StorageRepository storageRepository;

    @Test
    public void givenDocument_mustFindAllStoragesMatching() {
        final var storages = List.of(
                StorageTestFactory.createAnDefaultToInsert(),
                StorageTestFactory.createAnDefaultToInsert(),
                StorageTestFactory.createAnDefaultToInsertWithDocumentLinkedHash("PF32423")
        );
        storages.forEach(testEntityManager::persistAndFlush);

        final var storagesFoundPaged =
                storageRepository.findAllByDocumentLinkedHash(
                        StorageDefaultData.documentLinkedHash, Pageable.unpaged());
        final var storagesFound = storagesFoundPaged.getContent();

        Assert.assertThat(storagesFound, Matchers.hasSize(2));
    }

    @Test
    public void givenId_mustFindStorage() {
        final var storage = StorageTestFactory.createAnDefaultToInsert();
        final var storageId = (UUID) testEntityManager.persistAndGetId(storage);

        final var storageFound = storageRepository.findStorageById(storageId);

        Assertions.assertThat(storageFound).isEqualToIgnoringGivenFields(storage);
    }
}