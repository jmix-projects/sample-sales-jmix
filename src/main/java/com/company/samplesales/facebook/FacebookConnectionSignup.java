package com.company.samplesales.facebook;

import com.company.samplesales.entity.User;
import com.company.samplesales.security.FullAccessRole;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.UserRepository;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    private static final Logger log = LoggerFactory.getLogger(FacebookConnectionSignup.class);

    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        log.info("Signup " + connection.getDisplayName());

        // unfortunately connection.fetchUserData() doesn't work, so need to fetch data manually
        String accessKey = getAccessKey(connection);
        UserProfile profile = fetchData(accessKey);

        String email = profile.email;

        try {
            User existing = (User) userRepository.loadUserByUsername(email);
            if (existing.getSocialId() == null || !existing.getSocialId().equals(profile.id)) {
                // wrong user with the same email
                return null;
            }

            // found
            return existing.getUsername();
        } catch (UsernameNotFoundException e) {
            log.debug("Not found " + e.getMessage());
        }

        final User user = unconstrainedDataManager.create(User.class);
        user.setUsername(email);
        user.setFirstName(profile.first_name);
        user.setLastName(profile.last_name);
        user.setSocialId(profile.id);
        user.setEmail(email);
        user.setPassword(null);

        RoleAssignmentEntity roleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        roleAssignment.setUsername(user.getUsername());
        roleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);
        roleAssignment.setRoleCode(FullAccessRole.ROLE_NAME); // admin role

        unconstrainedDataManager.save(user, roleAssignment);

        return user.getUsername();
    }

    private String getAccessKey(Connection<?> connection) {
        Field field;
        try {
            field = OAuth2Connection.class.getDeclaredField("accessToken");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Error", e);
        }
        String accessKey = (String) ReflectionUtils.getField(field, connection);
        return accessKey;
    }

    /** The Constant LOGGED_USER_ID. */
    public static final String LOGGED_USER_ID = "me";

    public UserProfile fetchData(String accessToken) {
        FacebookTemplate facebook = new FacebookTemplate(accessToken);

        UserProfile facebookUser = facebook.fetchObject(LOGGED_USER_ID, UserProfile.class, "id", "email",
                "first_name", "last_name");
        return facebookUser;
    }

}
