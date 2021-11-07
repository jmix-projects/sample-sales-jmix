package com.company.samplesales.facebook;

import io.jmix.core.security.SecurityContextHelper;
import io.jmix.core.security.SystemAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class FacebookSignInAdapter implements SignInAdapter {
    private static final Logger log = LoggerFactory.getLogger(FacebookSignInAdapter.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        log.info("Sign In " + connection.getDisplayName() + " as " + localUserId);
        SystemAuthenticationToken systemAuthenticationToken = new SystemAuthenticationToken(localUserId);
        Authentication authentication = authenticationManager.authenticate(systemAuthenticationToken);

        SecurityContextHelper.setAuthentication(authentication);

        return null;
    }
}
