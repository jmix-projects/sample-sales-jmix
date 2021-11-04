package com.company.samplesales.security;

import io.jmix.ldap.userdetails.JmixLdapGrantedAuthoritiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class LdapAuthoritiesMapper {
    @Autowired
    private JmixLdapGrantedAuthoritiesMapper grantedAuthoritiesMapper;

    @PostConstruct
    public void postConstruct() {
        Map<String, String> authorityMap = new HashMap<>();
        authorityMap.put("admin", FullAccessRole.ROLE_NAME);
        authorityMap.put("managers", SellerRole.CODE);
        authorityMap.put("developers", ReaderRole.CODE);

        grantedAuthoritiesMapper.setAuthorityToRoleCodeMapper(s -> authorityMap.getOrDefault(s, s));
    }
}