package com.company.samplesales.keycloak.config;

import io.jmix.core.JmixOrder;
import io.jmix.core.security.AuthorizedUrlsProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

import java.util.Collections;
import java.util.Optional;

import static io.jmix.security.SecurityConfigurers.apiSecurity;

@Configuration
@EnableWebSecurity
@ConditionalOnBean(AuthorizedUrlsProvider.class)
@Order(JmixOrder.HIGHEST_PRECEDENCE + 100 + 1)
public class KeycloakResourceServerConfiguration extends WebSecurityConfigurerAdapter {

    private final KeycloakRolesMapper keycloakRolesMapper;
    private final KeycloakProperties keycloakProperties;

    public KeycloakResourceServerConfiguration(KeycloakRolesMapper keycloakRolesMapper, KeycloakProperties keycloakProperties) {
        this.keycloakRolesMapper = keycloakRolesMapper;
        this.keycloakProperties = keycloakProperties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.apply(apiSecurity())
                .and()
                .oauth2ResourceServer()
                .jwt()
                .decoder(keycloakJwtDecoder())
                .jwtAuthenticationConverter(keycloakJwtAuthenticationConverter());
    }

    @Bean("jmixkeycloakdemo_JwtDecoder")
    public JwtDecoder keycloakJwtDecoder() {
        String issuerUri = keycloakProperties.getIssuerUri();
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    @Bean("jmixkeycloakdemo_JwtAuthenticationConverter")
    public JwtUserDetailsAuthenticationConverter keycloakJwtAuthenticationConverter() {
        JwtUserDetailsAuthenticationConverter converter = new JwtUserDetailsAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt ->
                Optional.ofNullable(jwt.getClaimAsStringList("roles"))
                        .map(keycloakRolesMapper::createAuthorities)
                        .orElse(Collections.emptyList())
        );
        return converter;
    }

}
