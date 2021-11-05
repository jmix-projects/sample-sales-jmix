package com.company.samplesales.keycloak.config;

import com.company.samplesales.keycloak.user.OidcUserDetails;
import io.jmix.security.StandardSecurityConfiguration;
import io.jmix.security.role.ResourceRoleRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class KeycloakStandardSecurityConfiguration extends StandardSecurityConfiguration {

    public static final String ROLES = "roles";

    protected final KeycloakProperties keycloakProperties;
    protected final ResourceRoleRepository resourceRoleRepository;
    protected final ClientRegistrationRepository clientRegistrationRepository;
    protected final KeycloakRolesMapper keycloakRolesMapper;

    public KeycloakStandardSecurityConfiguration(KeycloakProperties keycloakProperties,
                                                 ResourceRoleRepository resourceRoleRepository,
                                                 ClientRegistrationRepository clientRegistrationRepository,
                                                 KeycloakRolesMapper keycloakRolesMapper) {
        this.keycloakProperties = keycloakProperties;
        this.resourceRoleRepository = resourceRoleRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.keycloakRolesMapper = keycloakRolesMapper;
    }

    protected OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri(keycloakProperties.getRedirectUri());
        return successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService())
                .and()
                .and()
                .logout().logoutSuccessHandler(oidcLogoutSuccessHandler());

        http.csrf().disable();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        OidcUserService delegate = new OidcUserService();
        return userRequest -> {
            OidcUser user = delegate.loadUser(userRequest);
            // todo "given_name", "family_name", "email", "sub"
            return new OidcUserDetails(getUserAuthorities(user), user.getIdToken(), user.getUserInfo());
        };
    }

    private Collection<? extends GrantedAuthority> getUserAuthorities(OAuth2User user) throws OAuth2AuthenticationException {
        ArrayList<String> roles = user.getAttribute(ROLES);
        if (roles != null && !roles.isEmpty()) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.addAll(user.getAuthorities());
            authorities.addAll(keycloakRolesMapper.createAuthorities(roles));
            return authorities;
        } else {
            return user.getAuthorities();
        }
    }
}
