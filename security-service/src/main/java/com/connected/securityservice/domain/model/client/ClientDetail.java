package com.connected.securityservice.domain.model.client;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ClientDetail implements ClientDetails {

    private static final long serialVersionUID = 1L;

    private String id;

    private String secret;

    private Integer tokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Set<String> authorizedGrantTypes;

    ClientDetail(final String id, final String secret,
                 final Integer tokenValiditySeconds,
                 final Integer refreshTokenValiditySeconds,
                 final Set<String> authorizedGrantTypes) {
        this.id = id;
        this.secret = secret;
        this.tokenValiditySeconds = tokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public String getClientId() {
        return id;
    }

    @Override
    public Set<String> getResourceIds() {
        return Set.of();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return secret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return Set.of();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Set.of();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Set.of();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return tokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(final String autoApprove) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Map.of();
    }
}
