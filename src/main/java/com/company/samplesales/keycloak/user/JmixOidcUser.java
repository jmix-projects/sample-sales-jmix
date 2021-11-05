package com.company.samplesales.keycloak.user;

import io.jmix.security.authentication.JmixUserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface JmixOidcUser extends JmixUserDetails, OidcUser {
    OidcUser getDelegate();
    void setDelegate(OidcUser delegate);
}
