package com.company.samplesales.keycloak.sync;

import com.company.samplesales.keycloak.user.JmixOidcUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;

public interface UserSynchronization {
    JmixOidcUser synchronizeUserDetails(OidcUser oidcUser, Collection<? extends GrantedAuthority> userAuthorities);
}
