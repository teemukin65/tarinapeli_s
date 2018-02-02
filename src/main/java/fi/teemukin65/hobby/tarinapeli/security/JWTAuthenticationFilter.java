package fi.teemukin65.hobby.tarinapeli.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerLoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static fi.teemukin65.hobby.tarinapeli.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            PlayerLoginDto playerCreds = new ObjectMapper()
                    .readValue(request.getInputStream(), PlayerLoginDto.class);
            LOGGER.debug("obtained playerCreds:{}", playerCreds);
            // TODO: authorities...
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            playerCreds.getEmail(),
                            playerCreds.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        LOGGER.info("successfulAuthentication with JWT token for user:{}", authResult.getPrincipal().toString());
        String token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date((System.currentTimeMillis() + EXPIRATION_TIME)))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
