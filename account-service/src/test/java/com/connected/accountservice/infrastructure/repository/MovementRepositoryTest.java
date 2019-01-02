package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
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
public class MovementRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MovementRepository movementRepository;

    private Storage defaultStorage;

    @Before
    public void init() {
        defaultStorage = StorageTestFactory.createAnDefaultToInsert();

        testEntityManager.persistAndFlush(defaultStorage);
    }

    @Test
    public void givenDocument_mustFindAllMovementsMatching() {
        final var movements = List.of(
                MovementTestFactory.createAnDefaultToInsertWithStorage(defaultStorage),
                MovementTestFactory.createAnDefaultToInsertWithStorage(defaultStorage),
                MovementTestFactory.createAnDefaultToInsertWithStorageAndDocumentLinkedHash(
                        defaultStorage, "PF32423")
        );
        movements.forEach(testEntityManager::persistAndFlush);

        final var movementsFoundPaged =
                movementRepository.findAllByDocumentLinkedHash(
                        StorageDefaultData.documentLinkedHash, Pageable.unpaged());
        final var movementsFound = movementsFoundPaged.getContent();

        Assert.assertThat(movementsFound, Matchers.hasSize(2));
    }

    @Test
    public void givenId_mustFindMovement() {
        final var movement = MovementTestFactory.createAnDefaultToInsertWithStorage(defaultStorage);
        final var movementId = (UUID) testEntityManager.persistAndGetId(movement);

        final var movementFound = movementRepository.findMovementById(movementId);

        Assertions.assertThat(movementFound)
                .isEqualToIgnoringGivenFields(movement, "storageId");
    }

}