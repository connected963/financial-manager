package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.storagevalue.StorageValueInputModelTestFactory;
import com.connected.accountservice.application.service.storagevalue.StorageValueService;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.common.defaultdata.storagevalue.StorageValueDefaultData;
import com.connected.accountservice.domain.querymodel.storagevalue.SimpleStorageValueQueryModelTestFactory;
import com.connected.accountservice.presentation.common.AbstractControllerMockTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

public class StorageValueControllerTest extends AbstractControllerMockTest {

    @MockBean
    private StorageValueService storageValueService;

    @Test
    public void givenStorageId_mustFindAllByStorageWithSuccess() throws Exception {
        final var expectedResult = List.of(
                SimpleStorageValueQueryModelTestFactory.createAnDefaultWithValue(100.0),
                SimpleStorageValueQueryModelTestFactory.createAnDefaultWithValue(200.0)
        );
        final var expectedResultPaged = new PageImpl<>(expectedResult);
        Mockito.when(storageValueService.findAllByStorageId(
                Mockito.eq(StorageDefaultData.id), Mockito.any(Pageable.class)))
                .thenReturn(expectedResultPaged);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResultPaged);

        final var url = String.format("/storage/%s/value", StorageDefaultData.id);
        performGet(url).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenStorageValueId_mustFindStorageValueWithSuccess() throws Exception {
        final var expectedResult = SimpleStorageValueQueryModelTestFactory.createAnDefault();
        Mockito.when(storageValueService.findById(StorageValueDefaultData.id))
                .thenReturn(expectedResult);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResult);

        final var url = String.format("/storage/value/%s", StorageValueDefaultData.id);
        performGet(url).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenStorageValueInputModelWithoutValue_mustFailToInsert() throws Exception {
        final var storageValueToInsert =
                StorageValueInputModelTestFactory.createAnDefaultToInsertWithoutValue();
        performPost("/storage/value", storageValueToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenStorageValueInputModelWithoutStorage_mustFailToInsert() throws Exception {
        final var storageValueToInsert =
                StorageValueInputModelTestFactory.createAnDefaultToInsertWithoutStorage();
        performPost("/storage/value", storageValueToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteStorageValueInputModel_mustInsertWithSuccess() throws Exception {
        final var storageValueToInsert =
                StorageValueInputModelTestFactory.createAnDefaultToInsert();
        performPost("/storage/value", storageValueToInsert)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenStorageValueInputModelWithoutValue_mustFailToUpdate() throws Exception {
        final var storageValueToUpdate =
                StorageValueInputModelTestFactory.createAnDefaultToUpdateWithoutValue();
        performPut("/storage/value", storageValueToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenStorageValueInputModelWithoutStorage_mustFailToUpdate() throws Exception {
        final var storageValueToUpdate =
                StorageValueInputModelTestFactory.createAnDefaultToUpdateWithoutStorage();
        performPost("/storage/value", storageValueToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteStorageValueInputModel_mustUpdateWithSuccess() throws Exception {
        final var storageValueToUpdate =
                StorageValueInputModelTestFactory.createAnDefaultToUpdate();
        performPut("/storage/value", storageValueToUpdate)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenStorageValueId_mustDeleteStorageValueWithSuccess() throws Exception {
        final var url = String.format("/storage/value/%s", StorageValueDefaultData.id);
        performDelete(url).andExpect(MockMvcResultMatchers.status().isOk());
    }
}