package com.connected.securityservice.infrastructure.repository.client;

import com.connected.securityservice.common.defaultdata.client.ClientDefaultData;
import com.connected.securityservice.domain.model.client.ClientTestFactory;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void givenCorrectClientId_mustFindClient() {
        final var client = ClientTestFactory.createAnDefault();
        testEntityManager.persistAndFlush(client);

        final var clientFoundOptional =
                clientRepository.findClientById(ClientDefaultData.id);
        final var clientFound = clientFoundOptional.get();

        Assertions.assertThat(clientFound).isEqualTo(client);
    }
}