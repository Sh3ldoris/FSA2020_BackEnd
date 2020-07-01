package sk.fullstack.lany.server.security;

/**
 * Properties of token that is generated.
 */
public class JwtProperties {

    public static final String SECRET = "MySignatureForTheToken";
    public static final int EXPIRATION_TIME = 7_200_000; // 2 HOURS
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_USERNAME = "Username";
    public static final String HEADER_ROLE = "Role";

}
