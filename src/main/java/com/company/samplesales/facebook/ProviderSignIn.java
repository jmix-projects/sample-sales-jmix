package com.company.samplesales.facebook;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinServletResponse;
import com.vaadin.server.VaadinServletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProviderSignIn implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(ProviderSignIn.class);

    private final ConnectionFactoryLocator connectionFactoryLocator;

    private ConnectSupport connectSupport;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public ProviderSignIn(ConnectionFactoryLocator connectionFactoryLocator) {
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

    public void redirectToFacebook() {
        try {
            ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory("facebook");
            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

            HttpServletRequest currentServletRequest = VaadinServletService.getCurrentServletRequest();
            HttpServletResponse httpServletResponse = VaadinServletService.getCurrentResponse().getHttpServletResponse();
            ServletWebRequest webRequest = new ServletWebRequest(currentServletRequest, httpServletResponse);

            String oAuthUrl = connectSupport.buildOAuthUrl(connectionFactory, webRequest, parameters);
            Page.getCurrent().setLocation(oAuthUrl);
        } catch (Exception e) {
            log.error("Exception while building authorization URL: ", e);
        }
    }

    // From InitializingBean
    public void afterPropertiesSet() throws Exception {
        this.connectSupport = new ConnectSupport(sessionStrategy);
        this.connectSupport.setUseAuthenticateUrl(true);
        connectSupport.setCallbackUrl("http://localhost:8080/signin/facebook");
    };

}
