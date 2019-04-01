package com.connected.securityservice.domain.model.client;

import com.connected.securityservice.common.defaultdata.client.ClientDefaultData;

public class ClientTestFactory {

    private ClientTestFactory() {

    }

    public static Client createAnDefault() {
        return new ClientTestBuilder()
                .withId(ClientDefaultData.id)
                .withSecret(ClientDefaultData.secret)
                .withTokenValiditySeconds(ClientDefaultData.tokenValiditySeconds)
                .withRefreshTokenValiditySeconds(ClientDefaultData.refreshTokenValiditySeconds)
                .withAuthorizedGrantTypes(ClientDefaultData.authorizedGrantTypes)
                .build();
    }

    public static Client createAnDefaultWithId(final String id) {
        return new ClientTestBuilder()
                .withId(id)
                .withSecret(ClientDefaultData.secret)
                .withTokenValiditySeconds(ClientDefaultData.tokenValiditySeconds)
                .withRefreshTokenValiditySeconds(ClientDefaultData.refreshTokenValiditySeconds)
                .withAuthorizedGrantTypes(ClientDefaultData.authorizedGrantTypes)
                .build();
    }
}
