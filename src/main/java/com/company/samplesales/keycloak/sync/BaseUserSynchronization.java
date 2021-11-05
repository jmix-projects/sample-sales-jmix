package com.company.samplesales.keycloak.sync;

import com.company.samplesales.keycloak.config.KeycloakProperties;
import com.company.samplesales.keycloak.user.JmixOidcUser;
import io.jmix.core.SaveContext;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.entity.EntityValues;
import io.jmix.core.security.UserRepository;
import io.jmix.security.authentication.RoleGrantedAuthority;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseUserSynchronization<T extends JmixOidcUser> implements UserSynchronization {

    private static final Logger log = LoggerFactory.getLogger(BaseUserSynchronization.class);

    @Autowired
    protected UnconstrainedDataManager dataManager;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected KeycloakProperties keycloakProperties;

    @Override
    public JmixOidcUser synchronizeUserDetails(OidcUser oidcUser, Collection<? extends GrantedAuthority> userAuthorities) {
        T jmixUserDetails;
        String username = oidcUser.getName();
        try {
            jmixUserDetails = (T) userRepository.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            log.info("User with login {} wasn't found in user repository", username);
            jmixUserDetails = createUserDetails(username, oidcUser);
        }

        jmixUserDetails.setDelegate(oidcUser);
        jmixUserDetails.setAuthorities(userAuthorities);

        //copy ldap attributes to UserDetails
        mapUserDetailsAttributes(jmixUserDetails, oidcUser);

        SaveContext saveContext = new SaveContext();
        if (keycloakProperties.isSynchronizeRoleAssignments()) {
            //clean previous role assignments
            List<RoleAssignmentEntity> existingRoleAssignments = dataManager.load(RoleAssignmentEntity.class)
                    .query("select e from sec_RoleAssignmentEntity e where e.username = :username")
                    .parameter("username", username)
                    .list();
            saveContext.removing(existingRoleAssignments);

            saveContext.saving(buildRoleAssignments(userAuthorities, username));
        }
        saveContext.saving(jmixUserDetails);

        //persist user details and roles if needed
        dataManager.save(saveContext);

        return jmixUserDetails;
    }

    protected abstract Class<T> getUserClass();

    protected T createUserDetails(String username, OidcUser oidcUser) {
        T userDetails = dataManager.create(getUserClass());
        EntityValues.setValue(userDetails, "username", username);
        return userDetails;
    }

    protected abstract void mapUserDetailsAttributes(T userDetails, OidcUser oidcUser);

    protected Collection<RoleAssignmentEntity> buildRoleAssignments(Collection<? extends GrantedAuthority> grantedAuthorities,
                                                                    String username) {
        List<RoleAssignmentEntity> roleAssignmentEntities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority instanceof RoleGrantedAuthority) {
                RoleGrantedAuthority roleGrantedAuthority = (RoleGrantedAuthority) grantedAuthority;
                String roleCode = roleGrantedAuthority.getAuthority();
                String roleType;
                if (roleCode.startsWith("row_level_role:")) {
                    roleType = RoleAssignmentRoleType.ROW_LEVEL;
                    roleCode = roleCode.substring("row_level_role:".length());
                } else {
                    roleType = RoleAssignmentRoleType.RESOURCE;
                }

                RoleAssignmentEntity roleAssignmentEntity = dataManager.create(RoleAssignmentEntity.class);
                roleAssignmentEntity.setRoleCode(roleCode);
                roleAssignmentEntity.setUsername(username);
                roleAssignmentEntity.setRoleType(roleType);
                roleAssignmentEntities.add(roleAssignmentEntity);
            }
        }
        return roleAssignmentEntities;
    }

}
