package com.company.samplesales.security;

import io.jmix.core.entity.EntityValues;
import io.jmix.core.security.UserRepository;
import io.jmix.security.authentication.RoleGrantedAuthority;
import io.jmix.security.model.ResourceRole;
import io.jmix.security.role.ResourceRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("sales_SimpleUserRepository")
public class SimpleUserRepository implements UserRepository {

    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Override
    public UserDetails getSystemUser() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        ResourceRole role = resourceRoleRepository.getRoleByCode("system-full-access");
        RoleGrantedAuthority authority = RoleGrantedAuthority.ofResourceRole(role);
        authorities.add(authority);

        return new User("system", "", authorities);
    }

    @Override
    public UserDetails getAnonymousUser() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User("anonymous", "", authorities);
    }

    @Override
    public List<? extends UserDetails> getByUsernameLike(String substring) {
        return Collections.emptyList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("Not supported");
    }
}