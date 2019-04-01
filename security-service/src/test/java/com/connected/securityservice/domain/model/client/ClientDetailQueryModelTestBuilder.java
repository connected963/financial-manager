package com.connected.securityservice.domain.model.client;

import java.util.Set;

public class ClientDetailQueryModelTestBuilder {

    private String id;

    private String secret;

    private Integer tokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Set<String> authorizedGrantTypes;

    public ClientDetailQueryModelTestBuilder() {
    }

    public ClientDetailQueryModelTestBuilder withId(final String id) {
        this.id = id;
        return this;
    }

    public ClientDetailQueryModelTestBuilder withSecret(final String secret) {
        this.secret = secret;
        return this;
    }

    public ClientDetailQueryModelTestBuilder withTokenValiditySeconds(final Integer tokenValiditySeconds) {
        this.tokenValiditySeconds = tokenValiditySeconds;
        return this;
    }

    public ClientDetailQueryModelTestBuilder withRefreshTokenValiditySeconds(
            final Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        return this;
    }

    public ClientDetailQueryModelTestBuilder withAuthorizedGrantTypes(
            final Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public ClientDetail build() {
        return new ClientDetail(
                id, secret, tokenValiditySeconds,
                refreshTokenValiditySeconds,
                authorizedGrantTypes);
    }
}
