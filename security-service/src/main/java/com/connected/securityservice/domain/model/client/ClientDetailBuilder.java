package com.connected.securityservice.domain.model.client;

import java.util.Set;

final class ClientDetailBuilder {

    private String id;

    private String secret;

    private Integer tokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Set<String> authorizedGrantTypes;

    ClientDetailBuilder() {

    }

    ClientDetailBuilder withId(final String id) {
        this.id = id;
        return this;
    }

    ClientDetailBuilder withSecret(final String secret) {
        this.secret = secret;
        return this;
    }

    ClientDetailBuilder withTokenValiditySeconds(final Integer tokenValiditySeconds) {
        this.tokenValiditySeconds = tokenValiditySeconds;
        return this;
    }

    ClientDetailBuilder withRefreshTokenValiditySeconds(
            final Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        return this;
    }

    ClientDetailBuilder withAuthorizedGrantTypes(
            final Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    ClientDetail build() {
        return new ClientDetail(id, secret, tokenValiditySeconds,
                refreshTokenValiditySeconds, authorizedGrantTypes);
    }
}
