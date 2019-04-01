package com.connected.securityservice.domain.model.client;

import com.connected.securityservice.common.defaultdata.client.ClientDefaultData;
import com.connected.securityservice.domain.common.converter.AuthorizedGrantTypeConverter;

public class ClientDetailQueryModelTestFactory {

    private ClientDetailQueryModelTestFactory() {

    }

    public static ClientDetail createAnDefault() {
        final var authorizedGrantTypesAsString =
                AuthorizedGrantTypeConverter.toStringSet(ClientDefaultData.authorizedGrantTypes);
        return new ClientDetailQueryModelTestBuilder()
                .withId(ClientDefaultData.id)
                .withSecret(ClientDefaultData.secret)
                .withTokenValiditySeconds(ClientDefaultData.tokenValiditySeconds)
                .withRefreshTokenValiditySeconds(ClientDefaultData.refreshTokenValiditySeconds)
                .withAuthorizedGrantTypes(authorizedGrantTypesAsString)
                .build();
    }

}
