package dev.alexfossa204.starbank.authorization.service.exception;

public class VerificationCodeNotGeneratedException extends  ServiceException {

    public VerificationCodeNotGeneratedException(String message) {
        super(message);
    }

    public VerificationCodeNotGeneratedException(Throwable cause) {
        super(cause);
    }
}
