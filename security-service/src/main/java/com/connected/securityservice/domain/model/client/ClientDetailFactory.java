package com.connected.securityservice.domain.model.client;

import com.connected.securityservice.domain.common.converter.AuthorizedGrantTypeConverter;

public final class ClientDetailFactory {

    private ClientDetailFactory() {

    }

    public static ClientDetail createBy(final Client client) {
        final var authorizedGrantTypes = client.getAuthorizedGrantTypes();
        final var authorizedGrantTypesAsString =
                AuthorizedGrantTypeConverter.toStringSet(authorizedGrantTypes);
        return new ClientDetailBuilder()
                .withId(client.getId())
                .withSecret(client.getSecret())
                .withTokenValiditySeconds(client.getTokenValiditySeconds())
                .withRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                .withAuthorizedGrantTypes(authorizedGrantTypesAsString)
                .build();
    }

}
