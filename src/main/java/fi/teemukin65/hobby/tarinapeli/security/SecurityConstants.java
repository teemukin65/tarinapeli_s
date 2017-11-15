package fi.teemukin65.hobby.tarinapeli.security;

public class SecurityConstants {
    static final String SECRET = "SecretForJWTS_SHOULDcomefromlocalhost";
    static final Long EXPIRATION_TIME = 10L * 24 * 60 * 60 * 1000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/game/player/sign-up";
    public static final String LOG_IN_URL = "/game/player/login";


}
