package com.connected.accountservice.application.service.storage;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModelTestFactory;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.storage.Storage;
import com.connected.accountservice.domain.model.storage.StorageFactory;
import com.connected.accountservice.domain.querymodel.storage.StorageQueryModelTestFactory;
import com.connected.accountservice.domain.validator.storage.StorageUpdateValidator;
import com.connected.accountservice.infrastructure.repository.StorageRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private StorageRepository storageRepository;

    private StorageService storageService;

    @Before
    public void init() {
        this.storageRepository = Mockito.mock(StorageRepository.class);
        this.storageService = new StorageService(storageRepository);
    }

    @Test
    public void givenNullDocumentLinkedHash_mustFailToFindAllByDocumentLinkedHash() {
        expectedException.expect(NullPointerException.class);

        storageService.findAllByDocumentLinkedHash(null, Pageable.unpaged());

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenDocumentLinkedHash_mustFindAllByDocumentLinkedHashWithSuccess() {
        Mockito.when(
                storageRepository.findAllByDocumentLinkedHash(
                        StorageDefaultData.documentLinkedHash, Pageable.unpaged()))
                .thenReturn(new PageImpl<>(List.of(
                        StorageQueryModelTestFactory.createAnDefault(),
                        StorageQueryModelTestFactory.createAnDefaultWithName("storage 2")
                )));

        final var foundStoragesPaged =
                storageService.findAllByDocumentLinkedHash(
                        StorageDefaultData.documentLinkedHash, Pageable.unpaged());
        final var foundStorages = foundStoragesPaged.getContent();


        Assert.assertThat(foundStorages, Matchers.hasSize(2));
    }

    @Test
    public void givenNullId_mustFailToFindById() {
        expectedException.expect(NullPointerException.class);

        storageService.findById(null);

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenId_mustFindByIdWithSuccess() {
        final var storage = StorageQueryModelTestFactory.createAnDefault();

        Mockito.when(
                storageRepository.findStorageById(StorageValueDefaultData.id))
                .thenReturn(storage);

        final var storageFound = storageService.findById(StorageValueDefaultData.id);

        Assert.assertEquals(StorageDefaultData.id, storage.getId());
        Assert.assertEquals(storage, storageFound);
    }

    @Test
    public void givenNullId_mustFailToFindEntityById() {
        expectedException.expect(NullPointerException.class);

        storageService.findEntityById(null);

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenId_mustFindEntityByIdWithSuccess() {
        final var storage = StorageQueryModelTestFactory.createAnDefault();

        Mockito.when(
                storageRepository.findStorageById(StorageValueDefaultData.id))
                .thenReturn(storage);

        final var storageFound = storageService.findById(StorageValueDefaultData.id);

        Assert.assertEquals(StorageDefaultData.id, storage.getId());
        Assertions.assertThat(storageFound).isEqualToIgnoringGivenFields(storage);
    }

    @Test
    public void givenNullStorageInputModel_mustFailToInsertStorage() {
        expectedException.expect(NullPointerException.class);

        storageService.insert(null);

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenStorageInputModel_mustInsertStorageWithSuccess() {
        Mockito.when(storageRepository.save(Mockito.any(Storage.class)))
                .thenAnswer(invocationOnMock -> {
                    final Storage storageToInsert = invocationOnMock.getArgument(0);
                    return storageToInsert.withId(UUID.randomUUID());
                });

        final var newStorage =
                StorageInputModelTestFactory.createAnDefaultToInsert();

        storageService.insert(newStorage);

        final var storageConvertedToInsert = StorageFactory.createByInputModel(newStorage);
        Mockito.verify(storageRepository).save(storageConvertedToInsert);
    }

    @Test
    public void givenNullStorageInputModel_mustFailToUpdateStorage() {
        expectedException.expect(NullPointerException.class);

        storageService.update(null);

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenStorageInputModelWithoutId_mustFailToUpdateStorage() {
        expectedException.expect(BusinessException.class);
        expectedException.expectMessage(StorageUpdateValidator.STORAGE_WITHOUT_ID);

        final var storageToUpdate = StorageInputModelTestFactory.createAnDefaultToInsert();

        storageService.update(storageToUpdate);

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenCompleteStorageInputModel_mustUpdateStorageWithSuccess() {
        final var storageToUpdate = StorageInputModelTestFactory.createAnDefaultToUpdate();
        storageService.update(storageToUpdate);

        final var storageConvertedToUpdate =
                StorageFactory.createByInputModel(storageToUpdate);

        Mockito.verify(storageRepository).save(storageConvertedToUpdate);
    }

    @Test
    public void givenNullId_mustFailToDeleteStorage() {
        expectedException.expect(NullPointerException.class);

        storageService.delete(null);

        Mockito.verifyZeroInteractions(storageRepository);
    }

    @Test
    public void givenId_mustDeleteStorageWithSuccess() {
        final var storageIdToDelete = UUID.randomUUID();
        storageService.delete(storageIdToDelete);

        Mockito.verify(storageRepository).deleteById(storageIdToDelete);
    }
}