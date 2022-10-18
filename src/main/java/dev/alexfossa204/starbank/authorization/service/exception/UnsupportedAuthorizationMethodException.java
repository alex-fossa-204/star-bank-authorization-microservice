package dev.alexfossa204.starbank.authorization.service.exception;

public class UnsupportedAuthorizationMethodException extends ServiceException {

    public UnsupportedAuthorizationMethodException(String message) {
        super(message);
    }

    public UnsupportedAuthorizationMethodException(Throwable cause) {
        super(cause);
    }
}
