package com.company.samplesales.keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class KeycloakClientRegistrationRepository {

    protected final KeycloakProperties keycloakProperties;

    public KeycloakClientRegistrationRepository(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    @Bean("jmixkeycloakdemo_ClientRegistrationRepository")
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
    }

    public ClientRegistration keycloakClientRegistration() {
        return ClientRegistrations.fromIssuerLocation(keycloakProperties.getIssuerUri())
                .registrationId("keycloak")
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .scope("openid")
                .userNameAttributeName("preferred_username")
                .build();
    }
}
