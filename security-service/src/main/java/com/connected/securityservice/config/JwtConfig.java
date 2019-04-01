package com.connected.securityservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JwtConfig {

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final KeyStoreConfig keyStoreConfig;

    @Autowired
    public JwtConfig(@Lazy final JwtAccessTokenConverter jwtAccessTokenConverter,
                     final KeyStoreConfig keyStoreConfig) {
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.keyStoreConfig = keyStoreConfig;
    }

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        final var converter = new JwtAccessTokenConverter();
        final var keystore = new ClassPathResource(keyStoreConfig.getName());
        final var keyStoreFactory = new KeyStoreKeyFactory(keystore, keyStoreConfig.getPasswordAsCharArray());
        final var keyPair = keyStoreFactory.getKeyPair(keyStoreConfig.getAlias());

        converter.setKeyPair(keyPair);

        return converter;
    }
}
