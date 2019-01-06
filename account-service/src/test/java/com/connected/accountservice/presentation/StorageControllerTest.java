package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.storage.StorageInputModelTestFactory;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import com.connected.accountservice.domain.querymodel.storage.StorageQueryModelTestFactory;
import com.connected.accountservice.presentation.common.AbstractControllerMockTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;

public class StorageControllerTest extends AbstractControllerMockTest {

    @MockBean
    private StorageService storageService;

    @Test
    public void givenDocumentLinkedHash_mustFindAllByDocumentLinkedHashWithSuccess() throws Exception {
        final var expectedResult = List.of(
                StorageQueryModelTestFactory.createAnDefaultWithName("Storage 1"),
                StorageQueryModelTestFactory.createAnDefaultWithName("Storage 2")
        );
        final var expectedResultPaged = new PageImpl<>(expectedResult);
        Mockito.when(storageService.findAllByDocumentLinkedHash(
                Mockito.eq(StorageDefaultData.documentLinkedHash), Mockito.any(Pageable.class)))
                .thenReturn(expectedResultPaged);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResultPaged);

        final Map<String, Object> parameters =
                Map.of("documentLinkedHash", StorageDefaultData.documentLinkedHash);
        performGet("/storage", parameters).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenStorageId_mustFindStorageWithSuccess() throws Exception {
        final var expectedResult = StorageQueryModelTestFactory.createAnDefault();
        Mockito.when(storageService.findById(StorageDefaultData.id))
                .thenReturn(expectedResult);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResult);

        final var url = String.format("/storage/%s", StorageDefaultData.id);
        performGet(url).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenStorageInputModelWithoutName_mustFailToInsert() throws Exception {
        final var storageToInsert =
                StorageInputModelTestFactory.createAnDefaultToInsertWithoutName();
        performPost("/storage", storageToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenStorageInputModelWithoutDocumentLinkedHash_mustFailToInsert() throws Exception {
        final var storageToInsert =
                StorageInputModelTestFactory.createAnDefaultToInsertWithoutDocumentLinkedHash();
        performPost("/storage", storageToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteStorageInputModel_mustInsertWithSuccess() throws Exception {
        final var storageToInsert = StorageInputModelTestFactory.createAnDefaultToInsert();
        performPost("/storage", storageToInsert)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenStorageInputModelWithoutName_mustFailToUpdate() throws Exception {
        final var storageToUpdate =
                StorageInputModelTestFactory.createAnDefaultToUpdateWithoutName();
        performPut("/storage", storageToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenStorageInputModelWithoutDocumentLinkedHash_mustFailToUpdate() throws Exception {
        final var storageToUpdate =
                StorageInputModelTestFactory.createAnDefaultToUpdateWithoutDocumentLinkedHash();
        performPut("/storage", storageToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteStorageInputModel_mustUpdateWithSuccess() throws Exception {
        final var storageToUpdate = StorageInputModelTestFactory.createAnDefaultToUpdate();
        performPut("/storage", storageToUpdate)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenStorageId_mustDeleteStorageWithSuccess() throws Exception {
        final var url = String.format("/storage/%s", StorageValueDefaultData.id);
        performDelete(url).andExpect(MockMvcResultMatchers.status().isOk());
    }
}