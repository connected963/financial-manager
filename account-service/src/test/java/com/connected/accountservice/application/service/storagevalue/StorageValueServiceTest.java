package com.connected.accountservice.application.service.storagevalue;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModel;
import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModelTestFactory;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import com.connected.accountservice.domain.model.storagevalue.StorageValue;
import com.connected.accountservice.domain.model.storagevalue.StorageValueFactory;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModel;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModelTestFactory;
import com.connected.accountservice.domain.validator.storagevalue.StorageValueUpdateValidator;
import com.connected.accountservice.infrastructure.repository.StorageValueRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@RunWith(MockitoJUnitRunner.class)
public class StorageValueServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private StorageValueRepository storageValueRepository;

    private StorageService storageService;

    private StorageValueService storageValueService;

    @Before
    public void init() {
        this.storageValueRepository = Mockito.mock(StorageValueRepository.class);
        this.storageService = Mockito.mock(StorageService.class);
        this.storageValueService = new StorageValueService(storageValueRepository, storageService);
    }

    @Test
    public void givenNullStorageId_mustFailToFindAllByStorageId() {
        expectedException.expect(NullPointerException.class);

        storageValueService.findAllByStorageId(null, Pageable.unpaged());

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenStorageId_mustFindAllByStorageIdWithSuccess() {
        Mockito.when(
                storageValueRepository.findAllByStorageId(StorageDefaultData.id, Pageable.unpaged()))
                .thenReturn(new PageImpl<>(List.of(
                        SimpleStorageValueQueryModelTestFactory.createAnDefault(),
                        SimpleStorageValueQueryModelTestFactory.createAnDefaultWithValue(123.3),
                        SimpleStorageValueQueryModelTestFactory.createAnDefaultWithValue(534.6)
                )));

        final var foundStorageValuesPaged =
                storageValueService.findAllByStorageId(StorageDefaultData.id, Pageable.unpaged());
        final var foundStorageValues = foundStorageValuesPaged.getContent();


        Assert.assertThat(foundStorageValues, Matchers.hasSize(3));

        final Predicate<SimpleStorageValueQueryModel> hasSameStorage =
                storageValue -> storageValue.getStorageId().equals(StorageDefaultData.id);
        Assertions.assertThat(foundStorageValues).allMatch(hasSameStorage);
    }

    @Test
    public void givenNullId_mustFailToFindById() {
        expectedException.expect(NullPointerException.class);

        storageValueService.findById(null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenId_mustFindByIdWithSuccess() {
        final var storageValue = SimpleStorageValueQueryModelTestFactory.createAnDefault();

        Mockito.when(
                storageValueRepository.findStorageValueById(StorageValueDefaultData.id))
                .thenReturn(storageValue);

        final var storageValueFound = storageValueService.findById(StorageValueDefaultData.id);

        Assert.assertEquals(StorageDefaultData.id, storageValue.getStorageId());
        Assertions.assertThat(storageValueFound)
                .isEqualToIgnoringGivenFields(storageValue, "storageId");
    }

    @Test
    public void givenNullStorageValueInputModel_mustFailToInsertStorageValue() {
        expectedException.expect(NullPointerException.class);

        storageValueService.insert(null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenStorageValueInputModel_mustInsertStorageValueWithSuccess() {
        final Storage storage = StorageTestFactory.createAnDefault();
        Mockito.when(storageService.findEntityById(Mockito.any(UUID.class)))
                .thenReturn(storage);

        Mockito.when(storageValueRepository.save(Mockito.any(StorageValue.class)))
                .thenAnswer(invocationOnMock -> {
                    final StorageValue storageValueToInsert = invocationOnMock.getArgument(0);
                    return storageValueToInsert.withId(UUID.randomUUID());
                });

        final StorageValueInputModel newStorageValue =
                StorageValueInputModelTestFactory.createAnDefaultToInsert();

        final var insertedStorageValue = storageValueService.insert(newStorageValue);
        final var storageValueConvertedToInsert =
                StorageValueFactory.createByInputModelWithStorage(newStorageValue, storage);

        Mockito.verify(storageValueRepository).save(storageValueConvertedToInsert);
        Assert.assertNotNull(insertedStorageValue.getId());
    }

    @Test
    public void givenNullStorageValueInputModel_mustFailToUpdateStorageValue() {
        expectedException.expect(NullPointerException.class);

        storageValueService.update(null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenStorageValueInputModelWithoutId_mustFailToUpdateStorageValue() {
        expectedException.expect(BusinessException.class);
        expectedException.expectMessage(StorageValueUpdateValidator.STORAGE_VALUE_WITHOUT_ID);

        final var storageValueToUpdate = StorageValueInputModelTestFactory.createAnDefaultToInsert();

        storageValueService.update(storageValueToUpdate);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenCompleteStorageValueInputModel_mustUpdateStorageValueWithSuccess() {
        final Storage storage = StorageTestFactory.createAnDefault();
        Mockito.when(storageService.findEntityById(Mockito.any(UUID.class)))
                .thenReturn(storage);

        final StorageValueInputModel storageValueToUpdate =
                StorageValueInputModelTestFactory.createAnDefaultToUpdate();

        storageValueService.update(storageValueToUpdate);
        final var storageValueConvertedToUpdate =
                StorageValueFactory.createByInputModelWithStorage(storageValueToUpdate, storage);

        Mockito.verify(storageValueRepository).save(storageValueConvertedToUpdate);
    }

    @Test
    public void givenNullId_mustFailToDeleteStorageValue() {
        expectedException.expect(NullPointerException.class);

        storageValueService.delete(null);

        Mockito.verifyZeroInteractions(storageValueRepository);
    }

    @Test
    public void givenId_mustDeleteStorageValueWithSuccess() {
        final var storageValueIdToDelete = UUID.randomUUID();
        storageValueService.delete(storageValueIdToDelete);

        Mockito.verify(storageValueRepository).deleteById(storageValueIdToDelete);
    }
}