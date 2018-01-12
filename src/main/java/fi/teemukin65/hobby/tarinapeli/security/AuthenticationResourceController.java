package fi.teemukin65.hobby.tarinapeli.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/api/session")
public class AuthenticationResourceController {

    private final
    UserDetailsService userDetailsService;

    private final
    AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationResourceController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User session(Principal user) {
        String name = user == null ? null : user.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        return new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }


}
