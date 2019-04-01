package com.connected.securityservice.application.service.client;

import com.connected.securityservice.domain.model.client.ClientDetailFactory;
import com.connected.securityservice.infrastructure.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailService implements ClientDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(final String id) {
        return clientRepository.findClientById(id)
                .map(ClientDetailFactory::createBy)
                .orElseThrow(() -> new ClientRegistrationException(
                        String.format("No client found with id '%s'.", id)));
    }
}
