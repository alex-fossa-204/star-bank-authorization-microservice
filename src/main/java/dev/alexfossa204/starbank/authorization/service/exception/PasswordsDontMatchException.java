package dev.alexfossa204.starbank.authorization.service.exception;

public class PasswordsDontMatchException extends ServiceException {

    public PasswordsDontMatchException(String message) {
        super(message);
    }

    public PasswordsDontMatchException(Throwable cause) {
        super(cause);
    }

}
