package dev.alexfossa204.starbank.authorization.service.exception;

public class VerificationCodeNotFoundException extends ServiceException {

    public VerificationCodeNotFoundException(String message) {
        super(message);
    }

    public VerificationCodeNotFoundException(Throwable cause) {
        super(cause);
    }
}
