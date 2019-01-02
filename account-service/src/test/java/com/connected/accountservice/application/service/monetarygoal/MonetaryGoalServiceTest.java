package com.connected.accountservice.application.service.monetarygoal;

import com.connected.accountservice.application.inputmodel.monetarygoal.MonetaryGoalInputModelTestFactory;
import com.connected.accountservice.application.service.storage.StorageService;
import com.connected.accountservice.common.defaultdata.monetarygoal.MonetaryGoalDefaultData;
import com.connected.accountservice.common.defaultdata.storage.StorageDefaultData;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.monetarygoal.MonetaryGoalFactory;
import com.connected.accountservice.domain.model.storage.StorageTestFactory;
import com.connected.accountservice.domain.querymodel.monetarygoal.SimpleMonetaryGoalQueryModelTestFactory;
import com.connected.accountservice.domain.validator.monetarygoal.MonetaryGoalUpdateValidator;
import com.connected.accountservice.infrastructure.repository.MonetaryGoalRepository;
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
public class MonetaryGoalServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MonetaryGoalRepository monetaryGoalRepository;

    private StorageService storageService;

    private MonetaryGoalService monetaryGoalService;

    @Before
    public void init() {
        this.monetaryGoalRepository = Mockito.mock(MonetaryGoalRepository.class);
        this.storageService = Mockito.mock(StorageService.class);
        this.monetaryGoalService =
                new MonetaryGoalService(monetaryGoalRepository, storageService);
    }

    @Test
    public void givenNullDocumentLinkedHash_mustFailToFindAllByDocumentLinkedHash() {
        expectedException.expect(NullPointerException.class);

        monetaryGoalService.findAllByDocumentLinkedHash(null, Pageable.unpaged());

        Mockito.verifyZeroInteractions(monetaryGoalRepository);
    }

    @Test
    public void givenDocumentLinkedHash_mustFindAllByDocumentLinkedHashWithSuccess() {
        Mockito.when(monetaryGoalService.findAllByDocumentLinkedHash(
                StorageDefaultData.documentLinkedHash, Pageable.unpaged()))
                .thenReturn(new PageImpl<>(List.of(
                        SimpleMonetaryGoalQueryModelTestFactory.createAnDefault(),
                        SimpleMonetaryGoalQueryModelTestFactory.createAnDefaultWithValue(324.9)
                )));

        final var monetaryGoalsFoundPaged =
                monetaryGoalService.findAllByDocumentLinkedHash(
                        StorageDefaultData.documentLinkedHash, Pageable.unpaged());
        final var monetaryGoalsFound = monetaryGoalsFoundPaged.getContent();


        Assert.assertThat(monetaryGoalsFound, Matchers.hasSize(2));
    }

    @Test
    public void givenNullId_mustFailToFindById() {
        expectedException.expect(NullPointerException.class);

        monetaryGoalService.findById(null);

        Mockito.verifyZeroInteractions(monetaryGoalRepository);
    }

    @Test
    public void givenId_mustFindByIdWithSuccess() {
        final var monetaryGoal = SimpleMonetaryGoalQueryModelTestFactory.createAnDefault();

        Mockito.when(monetaryGoalRepository.findMonetaryGoalById(MonetaryGoalDefaultData.id))
                .thenReturn(monetaryGoal);

        final var monetaryGoalFound = monetaryGoalService.findById(MonetaryGoalDefaultData.id);

        Assert.assertEquals(MonetaryGoalDefaultData.id, monetaryGoal.getId());
        Assert.assertEquals(monetaryGoal, monetaryGoalFound);
    }

    @Test
    public void givenNullMonetaryInputModel_mustFailToInsertMonetaryGoal() {
        expectedException.expect(NullPointerException.class);

        monetaryGoalService.insert(null);

        Mockito.verifyZeroInteractions(monetaryGoalRepository);
    }

    @Test
    public void givenMonetaryGoalInputModel_mustInsertMonetaryGoalWithSuccess() {
        final var storage = StorageTestFactory.createAnDefault();
        Mockito.when(storageService.findEntityById(StorageDefaultData.id))
                .thenReturn(storage);

        final var newMonetaryGoal = MonetaryGoalInputModelTestFactory.createAnDefaultToInsert();

        monetaryGoalService.insert(newMonetaryGoal);

        final var monetaryGoalConvertedToInsert =
                MonetaryGoalFactory.createByInputModelWithStorage(newMonetaryGoal, storage);
        Mockito.verify(monetaryGoalRepository).save(monetaryGoalConvertedToInsert);
    }

    @Test
    public void givenNullMonetaryGoalInputModel_mustFailToUpdateMonetaryGoal() {
        expectedException.expect(NullPointerException.class);

        monetaryGoalService.update(null);

        Mockito.verifyZeroInteractions(monetaryGoalRepository);
    }

    @Test
    public void givenMonetaryGoalInputModelWithoutId_mustFailToUpdateMonetaryGoal() {
        expectedException.expect(BusinessException.class);
        expectedException.expectMessage(MonetaryGoalUpdateValidator.MONETARY_GOAL_WITHOUT_ID);

        final var monetaryGoalToUpdate = MonetaryGoalInputModelTestFactory.createAnDefaultToInsert();

        monetaryGoalService.update(monetaryGoalToUpdate);

        Mockito.verifyZeroInteractions(monetaryGoalRepository);
    }

    @Test
    public void givenCompleteMonetaryGoalInputModel_mustUpdateMonetaryGoalWithSuccess() {
        final var storage = StorageTestFactory.createAnDefault();
        Mockito.when(storageService.findEntityById(StorageDefaultData.id))
                .thenReturn(storage);

        final var monetaryGoalToUpdate = MonetaryGoalInputModelTestFactory.createAnDefaultToUpdate();
        monetaryGoalService.update(monetaryGoalToUpdate);

        final var monetaryGoalConvertedToUpdate =
                MonetaryGoalFactory.createByInputModelWithStorage(monetaryGoalToUpdate, storage);

        Mockito.verify(monetaryGoalRepository).save(monetaryGoalConvertedToUpdate);
    }

    @Test
    public void givenNullId_mustFailToDeleteMonetaryGoal() {
        expectedException.expect(NullPointerException.class);

        monetaryGoalService.delete(null);

        Mockito.verifyZeroInteractions(monetaryGoalRepository);
    }

    @Test
    public void givenId_mustDeleteMonetaryGoalWithSuccess() {
        final var monetaryGoalIdToDelete = UUID.randomUUID();
        monetaryGoalService.delete(monetaryGoalIdToDelete);

        Mockito.verify(monetaryGoalRepository).deleteById(monetaryGoalIdToDelete);
    }

}