package com.connected.securityservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("token-store")
public class KeyStoreConfig {

    private String name;

    private String password;

    private String alias;

    public KeyStoreConfig() {
    }

    public char[] getPasswordAsCharArray() {
        return password.toCharArray();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
