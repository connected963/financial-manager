package com.connected.securityservice.domain.common.converter;

import com.connected.securityservice.domain.common.enums.AuthorizedGrantType;

import java.util.Set;
import java.util.stream.Collectors;

public final class AuthorizedGrantTypeConverter {

    private AuthorizedGrantTypeConverter() {

    }

    public static Set<String> toStringSet(final Set<AuthorizedGrantType> authorizedGrantTypes) {
        return authorizedGrantTypes.stream()
                .map(AuthorizedGrantType::name)
                .collect(Collectors.toSet());
    }

}
