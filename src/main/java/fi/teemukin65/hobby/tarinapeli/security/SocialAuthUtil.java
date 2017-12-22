package fi.teemukin65.hobby.tarinapeli.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.UserOperations;

public class SocialAuthUtil {
    protected static final Logger log = LoggerFactory.getLogger(SocialAuthUtil.class);

    public static void authenticate(Connection<?> connection) {
        Facebook facebook = (Facebook) connection.getApi();
//        https://stackoverflow.com/questions/39890885/error-message-is-12-bio-field-is-deprecated-for-versions-v2-8-and-higher
//        String [] supportedFields = {"id", "email",  "first_name", "last_name"};
        UserOperations username = facebook.userOperations();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User {} connected, facebook.", username);
    }
}
