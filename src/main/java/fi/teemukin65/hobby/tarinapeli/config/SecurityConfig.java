package fi.teemukin65.hobby.tarinapeli.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.savedrequest.NullRequestCache;

import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.LOGIN_URL;
import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.SIGN_UP_URL;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER + 1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(LOGIN_URL.concat("/**")).permitAll()
                .antMatchers("/index.html", "/css/**", "/js/**", "/media/**", "/static/**").permitAll()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache());


    }
}
