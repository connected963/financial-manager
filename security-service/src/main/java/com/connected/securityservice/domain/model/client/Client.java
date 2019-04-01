package com.connected.securityservice.domain.model.client;

import com.connected.securityservice.domain.common.enums.AuthorizedGrantType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column
    private String secret;

    @Column
    private Integer tokenValiditySeconds;

    @Column
    private Integer refreshTokenValiditySeconds;

    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AuthorizedGrantType.class)
    @CollectionTable(name = "client_authorizedGrantTypes",
            joinColumns = @JoinColumn(name = "client_id"))
    private Set<AuthorizedGrantType> authorizedGrantTypes;

    Client() {
    }

    Client(final String id, final String secret, final Integer tokenValiditySeconds,
           final Integer refreshTokenValiditySeconds,
           final Set<AuthorizedGrantType> authorizedGrantTypes) {
        this.id = id;
        this.secret = secret;
        this.tokenValiditySeconds = tokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    String getId() {
        return id;
    }

    String getSecret() {
        return secret;
    }

    Integer getTokenValiditySeconds() {
        return tokenValiditySeconds;
    }

    Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    Set<AuthorizedGrantType> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("secret", secret)
                .append("tokenValiditySeconds", tokenValiditySeconds)
                .append("refreshTokenValiditySeconds", refreshTokenValiditySeconds)
                .append("authorizedGrantTypes", authorizedGrantTypes)
                .toString();
    }
}
