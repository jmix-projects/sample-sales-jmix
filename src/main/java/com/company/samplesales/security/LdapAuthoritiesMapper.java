package com.company.samplesales.security;

import io.jmix.ldap.userdetails.JmixLdapGrantedAuthoritiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class LdapAuthoritiesMapper {
    @Autowired
    private JmixLdapGrantedAuthoritiesMapper grantedAuthoritiesMapper;

    @Autowired
    private UserDetailsContextMapper ldapUserDetailsMapper;

    @PostConstruct
    public void postConstruct() {
        Map<String, String> authorityMap = new HashMap<>();
        authorityMap.put("admin", FullAccessRole.ROLE_NAME);
        authorityMap.put("managers", SellerRole.CODE);
        authorityMap.put("developers", ReaderRole.CODE);

        grantedAuthoritiesMapper.setAuthorityToRoleCodeMapper(s -> authorityMap.getOrDefault(s, s));

        setupLdapUserDetailsMapper();
    }

    private void setupLdapUserDetailsMapper() {
        LdapUserDetailsMapper ldapUserDetailsMapper = (LdapUserDetailsMapper) this.ldapUserDetailsMapper;
        // take repeatable user attribute as role code
        // to test, assign a LDAP user an attribute with name "employeeType" and value "test-restrictions"
        //   - which is a code of TestRowLevelRole.
        ldapUserDetailsMapper.setRoleAttributes(new String[] {"employeeType"});
        ldapUserDetailsMapper.setRolePrefix("");
        ldapUserDetailsMapper.setConvertToUpperCase(false);
    }
}