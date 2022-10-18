package dev.alexfossa204.starbank.authorization.config.security.constant;

public class JwtTokenConfigurationConstant {

    public static final  String TOKEN_CANNOT_BE_VERIFIED_MSG = "Token cannot be verified";

    public static final String FORBIDDEN_MSG = "You need to log in to access this page";

    public static final String ACCESS_DENIED_MSG = "You do not has permission to access this page";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String JWT_TOKEN_HEADER = "Jwt-Token ";

    public static final int MAXIMAL_LOGIN_ATTEMPT_QUANTITY = Integer.MAX_VALUE;

    public static final int LOGIN_ATTEMPT_INCREMENT = 1;

}
