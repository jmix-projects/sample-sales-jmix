package com.company.samplesales.keycloak.config;

import io.jmix.core.annotation.Internal;
import io.jmix.security.authentication.RoleGrantedAuthority;
import io.jmix.security.model.ResourceRole;
import io.jmix.security.model.RowLevelRole;
import io.jmix.security.role.ResourceRoleRepository;
import io.jmix.security.role.RowLevelRoleRepository;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Internal
@Component("jmix_KeycloakRolesMapper")
public class KeycloakRolesMapper {

    private final ResourceRoleRepository resourceRoleRepository;
    private final RowLevelRoleRepository rowLevelRoleRepository;

    public KeycloakRolesMapper(ResourceRoleRepository resourceRoleRepository,
                               RowLevelRoleRepository rowLevelRoleRepository) {
        this.resourceRoleRepository = resourceRoleRepository;
        this.rowLevelRoleRepository = rowLevelRoleRepository;
    }

    @Nullable
    public GrantedAuthority createAuthority(String group) {
        int separator = group.indexOf('$');
        if (separator >= 0) {
            String roleType = group.substring(0, separator);
            String roleCode = group.substring(separator + 1);
            if (RoleAssignmentRoleType.RESOURCE.equals(roleType)) {
                ResourceRole role = resourceRoleRepository.findRoleByCode(roleCode);
                if (role != null) {
                    return RoleGrantedAuthority.ofResourceRole(role);
                }
            } else if (RoleAssignmentRoleType.ROW_LEVEL.equals(roleType)) {
                RowLevelRole role = rowLevelRoleRepository.findRoleByCode(roleCode);
                if (role != null) {
                    return RoleGrantedAuthority.ofRowLevelRole(role);
                }
            }
        }
        return null;
    }

    public List<GrantedAuthority> createAuthorities(Collection<String> groups) {
        return groups.stream()
                .map(this::createAuthority)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
