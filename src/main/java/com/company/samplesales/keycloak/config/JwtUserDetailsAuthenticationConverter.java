package com.company.samplesales.keycloak.config;

import com.company.samplesales.keycloak.user.OidcUserDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.Assert;

import java.util.Collection;

public class JwtUserDetailsAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(@Nullable Jwt jwt) {
        Collection<GrantedAuthority> authorities = this.jwtGrantedAuthoritiesConverter.convert(jwt);

        String registrationId = jwt.getClaim("jti");

        OidcIdToken oidcIdToken = OidcIdToken.withTokenValue(jwt.getTokenValue())
                .claims(c -> c.putAll(jwt.getClaims()))
                .build();

        OidcUserDetails oAuth2UserDetails = new OidcUserDetails(authorities, oidcIdToken);
        return new OAuth2AuthenticationToken(oAuth2UserDetails, authorities, registrationId);
    }

    /**
     * Sets the {@link Converter Converter&lt;Jwt, Collection&lt;GrantedAuthority&gt;&gt;}
     * to use. Defaults to {@link JwtGrantedAuthoritiesConverter}.
     *
     * @param jwtGrantedAuthoritiesConverter The converter
     * @see JwtGrantedAuthoritiesConverter
     * @since 5.2
     */
    public void setJwtGrantedAuthoritiesConverter(Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
    }
}
