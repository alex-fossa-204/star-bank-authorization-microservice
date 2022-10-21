package dev.alexfossa204.starbank.microservice.service.exception;

public class VerificationCodeNotGeneratedException extends  ServiceException {

    public VerificationCodeNotGeneratedException(String message) {
        super(message);
    }

    public VerificationCodeNotGeneratedException(Throwable cause) {
        super(cause);
    }
}
