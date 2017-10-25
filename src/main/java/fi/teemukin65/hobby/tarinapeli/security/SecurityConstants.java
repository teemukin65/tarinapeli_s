package fi.teemukin65.hobby.tarinapeli.security;

public class SecurityConstants {
    public static final String SECRET = "SecretForJWTS_SHOULDcomefromlocalhost";
    public static final Long EXPIRATION_TIME = 10L * 24 * 60 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/game/player/sign-up";


}
