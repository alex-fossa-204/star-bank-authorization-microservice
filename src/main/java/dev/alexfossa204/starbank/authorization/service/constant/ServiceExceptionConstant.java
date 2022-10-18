package dev.alexfossa204.starbank.authorization.service.constant;

public class ServiceExceptionConstant {

    public static final String CREDENTIAL_NOT_FOUND_MSG = "Invalid credentials or credentials not found";

    public static final String PASSWORDS_DONT_MATCH_MSG = "Passwords dont match. Try again";

    public static final String UNSUPPORTED_AUTHORIZATION_METHOD_MSG = "Authentication type is not supported";

    public static final String VERIFICATION_CODE_NOT_FOUND_MSG = "Verification code not found";

    public static final String VERIFICATION_CODE_ALREADY_USED_OR_UNAVAILABLE = "Verification Code was already used or unavailable any more";

    public static final String STATUS_CODE_METHOD_NOT_ALLOWED = "This request method is not allowed on this endpoint";

    public static final String METHOD_NOT_SUPPORTED = "for this URL";

    public static final String STATUS_CODE_NOT_FOUND_CODE_URL_HANDLER_NOT_EXISTS = "There is no mapping for this URL";

    public static final String STATUS_CODE_UNAUTHORIZED_INCORRECT_CREDENTIALS = "Incorrect credentials";

    public static final String STATUS_CODE_UNAUTHORIZED_ACCOUNT_LOCKED = "Your account had been locked. Contact the administration or recover your credentials";

    public static final String STATUS_CODE_CONFLICT = "Conflict. Data was deleted from server";

    public static final String STATUS_CODE_INTERNAL_SERVER_ERROR = "Internal server error";

    public static final String STATUS_CODE_SUCCESS = "Success";

    public static final String STATUS_CODE_UNAUTHORIZED = "Unauthorized";

    public static final String STATUS_CODE_BAD_REQUEST = "Bad request";

    public static final String STATUS_CODE_OK_NEW_PASSWORD_SAVED_BY_VERIFICATION_CODE = "Password successfully saved";

    public static final String STATUS_CODE_OK_VERIFICATION_CODE_SENT_AS_MESSAGE_FOR_CLIENT = "Dear Client! Verification code was sent on your phone";

    public static final String STATUS_CODE_OK_VERIFICATION_CODE_SENT_AS_MESSAGE_FOR_NON_CLIENT = "Dear Non Client! Verification code was sent on your phone";

    public static final String STATUS_CODE_BAD_REQUEST_VERIFICATION_CODE_NOT_GENERATED = "Verification code not generated. Try again";

}
