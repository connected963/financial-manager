package com.connected.accountservice.presentation;

import com.connected.accountservice.application.inputmodel.movement.MovementInputModelTestFactory;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.common.defaultdata.movement.MovementDefaultData;
import com.connected.accountservice.domain.querymodel.movement.SimpleMovementQueryModelTestFactory;
import com.connected.accountservice.presentation.common.AbstractControllerMockTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;

public class MovementControllerTest extends AbstractControllerMockTest {

    @MockBean
    private MovementService movementService;

    @Test
    public void givenDocumentLinkedHash_mustFindAllByDocumentLinkedHashWithSuccess() throws Exception {
        final var expectedResult = List.of(
                SimpleMovementQueryModelTestFactory.createAnDefaultWithValue(20.0),
                SimpleMovementQueryModelTestFactory.createAnDefaultWithValue(10.0)
        );
        final var expectedResultPaged = new PageImpl<>(expectedResult);
        Mockito.when(movementService.findAllByDocumentLinkedHash(
                Mockito.eq(MovementDefaultData.documentLinkedHash), Mockito.any(Pageable.class)))
                .thenReturn(expectedResultPaged);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResultPaged);

        final Map<String, Object> parameters =
                Map.of("documentLinkedHash", MovementDefaultData.documentLinkedHash);
        performGet("/movement", parameters).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenStorageId_mustFindStorageWithSuccess() throws Exception {
        final var expectedResult = SimpleMovementQueryModelTestFactory.createAnDefault();
        Mockito.when(movementService.findById(MovementDefaultData.id))
                .thenReturn(expectedResult);

        final var expectedResultConvertedToJson = objectMapper.writeValueAsString(expectedResult);

        final var url = String.format("/movement/%s", MovementDefaultData.id);
        performGet(url).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResultConvertedToJson));
    }

    @Test
    public void givenMovementInputModelWithoutType_mustFailToInsert() throws Exception {
        final var movementToInsert =
                MovementInputModelTestFactory.createAnDefaultToInsertWithoutType();
        performPost("/movement", movementToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMovementInputModelWithoutValue_mustFailToInsert() throws Exception {
        final var movementToInsert =
                MovementInputModelTestFactory.createAnDefaultToInsertWithoutValue();
        performPost("/movement", movementToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMovementInputModelWithoutStorage_mustInsertWithSuccess() throws Exception {
        final var movementToInsert =
                MovementInputModelTestFactory.createAnDefaultToInsertWithoutStorage();
        performPost("/movement", movementToInsert)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenMovementInputModelWithoutRepeatMonthly_mustFailToInsert() throws Exception {
        final var movementToInsert =
                MovementInputModelTestFactory.createAnDefaultToInsertWithoutRepeatMonthly();
        performPost("/movement", movementToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMovementInputModelWithoutDocumentLinkedHash_mustFailToInsert() throws Exception {
        final var movementToInsert =
                MovementInputModelTestFactory.createAnDefaultToInsertWithoutDocumentLinkedHash();
        performPost("/movement", movementToInsert)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteMovementInputModel_mustInsertWithSuccess() throws Exception {
        final var movementToInsert = MovementInputModelTestFactory.createAnDefaultToInsert();
        performPost("/movement", movementToInsert)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenMovementInputModelWithoutType_mustFailToUpdate() throws Exception {
        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdateWithoutType();
        performPut("/movement", movementToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMovementInputModelWithoutValue_mustFailToUpdate() throws Exception {
        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdateWithoutValue();
        performPut("/movement", movementToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMovementInputModelWithoutStorageId_mustUpdateWithSuccess() throws Exception {
        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdateWithoutStorage();
        performPut("/movement", movementToUpdate)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenMovementInputModelWithoutRepeatMonthly_mustFailToUpdate() throws Exception {
        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdateWithoutRepeatMonthly();
        performPut("/movement", movementToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenMovementInputModelWithoutDocumentLinkedHash_mustFailToUpdate() throws Exception {
        final var movementToUpdate =
                MovementInputModelTestFactory.createAnDefaultToUpdateWithoutDocumentLinkedHash();
        performPut("/movement", movementToUpdate)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenCompleteMovementInputModel_mustUpdateWithSuccess() throws Exception {
        final var movementToUpdate = MovementInputModelTestFactory.createAnDefaultToUpdate();
        performPut("/movement", movementToUpdate)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenMovementId_mustDeleteMovementWithSuccess() throws Exception {
        final var url = String.format("/movement/%s", MovementDefaultData.id);
        performDelete(url).andExpect(MockMvcResultMatchers.status().isOk());
    }

}