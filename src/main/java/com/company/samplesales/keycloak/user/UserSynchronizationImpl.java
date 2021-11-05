package com.company.samplesales.keycloak.user;

import com.company.samplesales.entity.User;
import com.company.samplesales.keycloak.sync.BaseUserSynchronization;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component("sales_UserSynchronizationImpl")
public class UserSynchronizationImpl extends BaseUserSynchronization<User> {
    @Override
    protected Class<User> getUserClass() {
        return User.class;
    }

    @Override
    protected void mapUserDetailsAttributes(User userDetails, OidcUser oidcUser) {
        userDetails.setFirstName(oidcUser.getUserInfo().getGivenName());
        userDetails.setLastName(oidcUser.getUserInfo().getFamilyName());
        userDetails.setEmail(oidcUser.getUserInfo().getEmail());
        userDetails.setTelephoneNumber(oidcUser.getUserInfo().getPhoneNumber());
    }
}