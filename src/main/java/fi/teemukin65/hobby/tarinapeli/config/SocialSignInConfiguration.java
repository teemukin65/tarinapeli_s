package fi.teemukin65.hobby.tarinapeli.config;

import fi.teemukin65.hobby.tarinapeli.security.SocialAuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.SignInAdapter;


@Configuration
public class SocialSignInConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SocialSignInConfiguration.class);

    @Bean
    public SignInAdapter authSignInAdapter() {
        return (userId, connection, request) -> {
            log.debug("authSignInAdapter, for user:{}, in request:{}", userId, request.toString());
            SocialAuthUtil.authenticate(connection);
            return null;
        };
    }
}

