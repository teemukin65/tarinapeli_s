package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.dao.PlayerRepository;
import fi.teemukin65.hobby.tarinapeli.domain.Player;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class DefaultUserDetailsService implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DefaultUserDetailsService() {
        super();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        authorityList.add(authority);

        try {
            final Player player = playerRepository.findByEmail(email);
            if (player == null) {
                throw new UsernameNotFoundException("No player found with username: " + email);
            }

            User user = modelMapper.map(player, User.class);
            LOGGER.debug("player initially modelmapped to user:{}", user);

            return user;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

}
