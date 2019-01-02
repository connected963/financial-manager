package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.domain.model.monetarygoal.MonetaryGoalTestFactory;
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
public class MonetaryGoalRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MonetaryGoalRepository monetaryGoalRepository;

    private Storage defaultStorage;

    @Before
    public void init() {
        defaultStorage = StorageTestFactory.createAnDefaultToInsert();

        testEntityManager.persistAndFlush(defaultStorage);
    }

    @Test
    public void givenDocument_mustFindAllMonetaryGoalsMatching() {
        final var unwantedStorage =
                StorageTestFactory.createAnDefaultToInsertWithDocumentLinkedHash("PF32423");
        testEntityManager.persistAndFlush(unwantedStorage);

        final var monetaryGoals = List.of(
                MonetaryGoalTestFactory.createAnDefaultToInsertWithStorage(defaultStorage),
                MonetaryGoalTestFactory.createAnDefaultToInsertWithStorage(defaultStorage),
                MonetaryGoalTestFactory.createAnDefaultToInsertWithStorage(unwantedStorage)
        );
        monetaryGoals.forEach(testEntityManager::persistAndFlush);

        final var monetaryGoalsFoundPaged =
                monetaryGoalRepository.findAllByDocumentLinkedHash(
                        StorageDefaultData.documentLinkedHash, Pageable.unpaged());
        final var movementsFound = monetaryGoalsFoundPaged.getContent();

        Assert.assertThat(movementsFound, Matchers.hasSize(2));
    }

    @Test
    public void givenId_mustFindMovement() {
        final var monetaryGoal =
                MonetaryGoalTestFactory.createAnDefaultToInsertWithStorage(defaultStorage);
        final var monetaryGoalId = (UUID) testEntityManager.persistAndGetId(monetaryGoal);

        final var monetaryGoalFound = monetaryGoalRepository.findMonetaryGoalById(monetaryGoalId);

        Assertions.assertThat(monetaryGoalFound)
                .isEqualToIgnoringGivenFields(monetaryGoal, "storageId");
    }
}