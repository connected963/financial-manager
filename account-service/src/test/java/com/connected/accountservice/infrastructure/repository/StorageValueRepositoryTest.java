package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import com.connected.accountservice.domain.model.storagevalue.StorageValue;
import com.connected.accountservice.domain.model.storagevalue.StorageValueTestFactory;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModel;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StorageValueRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StorageValueRepository storageValueRepository;

    private Storage defaultStorage;

    private UUID defaultStorageId;

    @Before
    public void init() {
        defaultStorage = StorageTestFactory.createAnDefaultToInsert();

        defaultStorageId = (UUID) testEntityManager.persistAndGetId(defaultStorage);
    }

    @Test
    public void givenStorageId_mustFindAllStorageValuesMatching() {
        final var unwantedStorage = StorageTestFactory.createAnDefaultToInsert();
        testEntityManager.persistAndFlush(unwantedStorage);

        final var storageValuesToInsert = List.of(
                StorageValueTestFactory.createAnDefaultToInsertWithStorage(defaultStorage),
                StorageValueTestFactory.createAnDefaultToInsertWithStorageAndValue(defaultStorage, 400.0),
                StorageValueTestFactory.createAnDefaultToInsertWithStorage(unwantedStorage)
        );
        storageValuesToInsert.forEach(testEntityManager::persistAndFlush);

        final Page<SimpleStorageValueQueryModel> storageValuesFoundPaged =
                storageValueRepository.findAllByStorageId(defaultStorageId, Pageable.unpaged());
        final List<SimpleStorageValueQueryModel> storageValuesFound =
                storageValuesFoundPaged.getContent();

        Assert.assertThat(storageValuesFound, Matchers.hasSize(2));

        final Predicate<SimpleStorageValueQueryModel> hasDefaultStorage =
                storageValue -> storageValue.getStorageId().equals(defaultStorageId);
        Assertions.assertThat(storageValuesFound)
                .allMatch(hasDefaultStorage);
    }

    @Test
    public void givenStorage_mustFindLastStorageValue() {
        final var expectedStorageValue =
                StorageValueTestFactory.createAnDefaultToInsertWithStorageAndValue(defaultStorage, 247.93);
        final var storageValuesToInsert = List.of(
                StorageValueTestFactory.createAnDefaultToInsertWithStorageAndValue(defaultStorage, 100.3),
                StorageValueTestFactory.createAnDefaultToInsertWithStorageAndValue(defaultStorage, 320.5),
                StorageValueTestFactory.createAnDefaultToInsertWithStorageAndValue(defaultStorage, 500.54),
                expectedStorageValue
        );
        storageValuesToInsert.forEach(testEntityManager::persistAndFlush);

        final Optional<StorageValue> lastStorageValue =
                storageValueRepository.findFirstByStorageEqualsOrderByDateDesc(defaultStorage);

        Assert.assertEquals(expectedStorageValue, lastStorageValue.orElseThrow());
    }

    @Test
    public void givenStorageValueId_mustFindStorageValue() {
        final var expectedStorageValue = StorageValueTestFactory.createAnDefaultToInsertWithStorage(defaultStorage);
        final var expectedStorageValueId = (UUID) testEntityManager.persistAndGetId(expectedStorageValue);

        final var storageValueFound = storageValueRepository.findStorageValueById(expectedStorageValueId);

        Assertions.assertThat(storageValueFound)
                .isEqualToIgnoringGivenFields(expectedStorageValue, "storageId");
    }

}