package fi.teemukin65.hobby.tarinapeli.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by teemu on 28.4.2017.
 */
public enum PlayerRole implements GrantedAuthority {
    ROLE_PLAYER {
        @Override
        public String getAuthority() {
            return "ROLE_PLAYER";
        }
    },
    ROLE_GAME_INITIATOR {
        @Override
        public String getAuthority() {
            return "ROLE_GAME_INITIATOR";
        }
    },
    ROLE_GAME_ADMIN {
        @Override
        public String getAuthority() {
            return "ROLE_GAME_ADMIN";
        }
    }
}
