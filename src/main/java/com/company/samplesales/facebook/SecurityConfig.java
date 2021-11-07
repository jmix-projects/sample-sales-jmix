package com.company.samplesales.facebook;

import io.jmix.security.StandardSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends StandardSecurityConfiguration {
    
    @Value("${spring.social.facebook.appSecret}")
    String appSecret;
    
    @Value("${spring.social.facebook.appId}")
    String appId;

    @Value("${sales.demo.postSignInUrl}")
    String postSignInUrl;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

    @Bean
    public ProviderSignInController providerSignInController() {
        ConnectionFactoryLocator connectionFactoryLocator = connectionFactoryLocator();
        UsersConnectionRepository usersConnectionRepository = getUsersConnectionRepository(connectionFactoryLocator);
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
        ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, facebookSignInAdapter());
        providerSignInController.setPostSignInUrl(postSignInUrl);
        return providerSignInController;
    }

    @Bean
    public FacebookSignInAdapter facebookSignInAdapter() {
        return new FacebookSignInAdapter();
    }
    
    private ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(appId, appSecret);
        connectionFactory.setScope("public_profile,email");
        registry.addConnectionFactory(connectionFactory);
        return registry;
    }

    private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
    }

    @Bean
    public ProviderSignIn providerSignIn() {
        ConnectionFactoryLocator connectionFactoryLocator = connectionFactoryLocator();
        UsersConnectionRepository usersConnectionRepository = getUsersConnectionRepository(connectionFactoryLocator);
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignIn(connectionFactoryLocator);
    }
}