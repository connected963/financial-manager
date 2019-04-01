package com.connected.securityservice.common.defaultdata.client;

import com.connected.securityservice.domain.common.converter.AuthorizedGrantTypeConverter;
import com.connected.securityservice.domain.common.enums.AuthorizedGrantType;

import java.util.Set;

public class ClientDefaultData {

    public static final String id = "Client Id";

    public static final String secret = "secret";

    public static final Integer tokenValiditySeconds = 30;

    public static final Integer refreshTokenValiditySeconds = 900;

    public static final Set<AuthorizedGrantType> authorizedGrantTypes =
            Set.of(AuthorizedGrantType.AUTHORIZATION_CODE, AuthorizedGrantType.PASSWORD);

    private ClientDefaultData() {

    }

    public static Set<String> authorizedGrantTypesAsString() {
        return AuthorizedGrantTypeConverter.toStringSet(authorizedGrantTypes);
    }
}
