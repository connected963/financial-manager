package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModelTestFactory;
import com.connected.accountservice.application.service.monetarygoal.MonetaryGoalService;
import com.connected.accountservice.common.defaultdata.monetarygoal.MonetaryGoalDefaultData;
import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.domain.querymodel.monetarygoal.SimpleMonetaryGoalQueryModelTestFactory;
import com.connected.accountservice.presentation.common.AbstractControllerMockTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;

public class MonetaryGoalControllerTest extends AbstractControllerMockTest {

    @MockBean
    private MonetaryGoalService monetaryGoalService;

    @Test
    public void givenDocumentLinkedHash_mustFindAllByDocumentLinkedHashWithSuccess() throws Exception {
        final var expectedResult = List.of(
                SimpleMonetaryGoalQueryModelTestFactory.createAnDefaultWithValue(20.0),
                SimpleMonetaryGoalQueryModelTestFactory.createAnDefaultWithValue(10.0)
        );
        final var expectedResultPaged = new PageImpl<>(expectedResult);
        Mockito.when(monetaryGoalService.findAllByDocumentLinkedHash(
                Mockito.eq(StorageDefaultData.documentLinkedHash), Mockito.any(Pageable.class)))
                .thenReturn(expectedResultPaged);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResultPaged);

        final Map<String, Object> parameters =
                Map.of("documentLinkedHash", MovementDefaultData.documentLinkedHash);
        performGet("/monetarygoal", parameters)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenMonetaryGoalId_mustFindMonetaryGoalWithSuccess() throws Exception {
        final var expectedResult = SimpleMonetaryGoalQueryModelTestFactory.createAnDefault();
        Mockito.when(monetaryGoalService.findById(MonetaryGoalDefaultData.id))
                .thenReturn(expectedResult);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResult);

        final var url = String.format("/monetarygoal/%s", MonetaryGoalDefaultData.id);
        performGet(url).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenMonetaryGoalInputModelWithoutValue_mustFailToInsert() throws Exception {
        final var monetaryGoalToInsert =
                MonetaryGoalInputModelTestFactory.createAnDefaultToInsertWithoutValue();
        performPost("/monetarygoal", monetaryGoalToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMonetaryGoalInputModelWithoutStorageId_mustFailToInsert() throws Exception {
        final var monetaryGoalToInsert =
                MonetaryGoalInputModelTestFactory.createAnDefaultToInsertWithoutStorage();
        performPost("/monetarygoal", monetaryGoalToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteMonetaryGoalInputModel_mustInsertWithSuccess() throws Exception {
        final var monetaryGoalToInsert = MonetaryGoalInputModelTestFactory.createAnDefaultToInsert();
        performPost("/monetarygoal", monetaryGoalToInsert)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenMonetaryGoalInputModelWithoutValue_mustFailToUpdate() throws Exception {
        final var monetaryGoalToUpdate =
                MonetaryGoalInputModelTestFactory.createAnDefaultToUpdateWithoutValue();
        performPut("/monetarygoal", monetaryGoalToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMonetaryGoalInputModelWithoutStorageId_mustFailToUpdate() throws Exception {
        final var monetaryGoalToUpdate =
                MonetaryGoalInputModelTestFactory.createAnDefaultToUpdateWithoutStorage();
        performPut("/monetarygoal", monetaryGoalToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteMonetaryGoalInputModel_mustUpdateWithSuccess() throws Exception {
        final var monetaryGoalToUpdate = MonetaryGoalInputModelTestFactory.createAnDefaultToUpdate();
        performPut("/monetarygoal", monetaryGoalToUpdate)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenMonetaryGoalId_mustDeleteMovementWithSuccess() throws Exception {
        final var url = String.format("/monetarygoal/%s", MonetaryGoalDefaultData.id);
        performDelete(url).andExpect(MockMvcResultMatchers.status().isOk());
    }

}