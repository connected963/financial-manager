package com.connected.securityservice.domain.model.client;

import com.connected.securityservice.domain.common.enums.AuthorizedGrantType;

import java.util.Set;

public class ClientTestBuilder {

    private String id;

    private String secret;

    private Integer tokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Set<AuthorizedGrantType> authorizedGrantTypes;

    public ClientTestBuilder() {
    }

    public ClientTestBuilder withId(final String id) {
        this.id = id;
        return this;
    }

    public ClientTestBuilder withSecret(final String secret) {
        this.secret = secret;
        return this;
    }

    public ClientTestBuilder withTokenValiditySeconds(final Integer tokenValiditySeconds) {
        this.tokenValiditySeconds = tokenValiditySeconds;
        return this;
    }

    public ClientTestBuilder withRefreshTokenValiditySeconds(
            final Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        return this;
    }

    public ClientTestBuilder withAuthorizedGrantTypes(
            final Set<AuthorizedGrantType> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public Client build() {
        return new Client(id, secret, tokenValiditySeconds,
                refreshTokenValiditySeconds, authorizedGrantTypes);
    }
}
