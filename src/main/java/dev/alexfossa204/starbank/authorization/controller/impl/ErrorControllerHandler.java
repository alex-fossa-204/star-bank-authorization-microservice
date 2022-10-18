package dev.alexfossa204.starbank.authorization.controller.impl;

import dev.alexfossa204.starbank.authorization.service.dto.HttpResponseDto;
import dev.alexfossa204.starbank.authorization.service.exception.PasswordsDontMatchException;
import dev.alexfossa204.starbank.authorization.service.exception.UnsupportedAuthorizationMethodException;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeExpiredException;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorControllerHandler implements ErrorController {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<HttpResponseDto> noHandlerFoundExceptionHandler() {
        return createHttpResponse(NOT_FOUND, STATUS_CODE_NOT_FOUND_CODE_URL_HANDLER_NOT_EXISTS);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<HttpResponseDto> badCredentialsExceptionHandler() {
        return createHttpResponse(UNAUTHORIZED, STATUS_CODE_UNAUTHORIZED_INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(value = LockedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<HttpResponseDto> lockedException() {
        return createHttpResponse(UNAUTHORIZED, STATUS_CODE_UNAUTHORIZED_ACCOUNT_LOCKED);
    }

    @ExceptionHandler(value = NoResultException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<HttpResponseDto> notFoundExceptionHandler(NoResultException exception) {
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(value = UnsupportedOperationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> unsupportedOperationExceptionHandler(UnsupportedOperationException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ResponseEntity<HttpResponseDto> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception) {
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format("%s %s", exception.getMessage(), METHOD_NOT_SUPPORTED));
    }

    @ExceptionHandler(value = UnsupportedAuthorizationMethodException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> unsupportedAuthorizationMethodExceptionHandler(UnsupportedAuthorizationMethodException serviceException) {
        return createHttpResponse(BAD_REQUEST, serviceException.getMessage());
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> userNameNotFoundExceptionHandler(UsernameNotFoundException serviceException) {
        return createHttpResponse(BAD_REQUEST, serviceException.getMessage());
    }

    @ExceptionHandler(value = PasswordsDontMatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> passwordsDontMatchExceptionHandler(PasswordsDontMatchException serviceException) {
        return createHttpResponse(BAD_REQUEST, serviceException.getMessage());
    }

    @ExceptionHandler(value = VerificationCodeNotFoundException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<HttpResponseDto> verificationCodeNotFoundExceptionHandler(VerificationCodeNotFoundException serviceException) {
        return createHttpResponse(UNAUTHORIZED, serviceException.getMessage());
    }

    @ExceptionHandler(value = VerificationCodeExpiredException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> verificationCodeExpiredExceptionHandler(VerificationCodeExpiredException serviceException) {
        return createHttpResponse(BAD_REQUEST, serviceException.getMessage());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<HttpResponseDto> noSuchElementExceptionHandler(NoSuchElementException serviceException) {
        return createHttpResponse(NOT_FOUND, serviceException.getMessage());
    }

    @ExceptionHandler(value = InvalidFormatException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> invalidFormatExceptionHandler(InvalidFormatException invalidFormatException) {
        String message = invalidFormatException.getOriginalMessage();
        return createHttpResponse(BAD_REQUEST, message);
    }

    @ExceptionHandler(value = JsonParseException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> jsonParseExceptionHandler(JsonParseException invalidFormatException) {
        String message = invalidFormatException.getOriginalMessage();
        return createHttpResponse(BAD_REQUEST, message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<String> errorsList = fieldErrorList.stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return createHttpResponse(BAD_REQUEST, errorsList);
    }

    private ResponseEntity<HttpResponseDto> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(httpStatus)
                .message(message)
                .build(), httpStatus);
    }

    private ResponseEntity<HttpResponseDto> createHttpResponse(HttpStatus httpStatus, List<String> messages) {
        return new ResponseEntity<>(HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(httpStatus)
                .message(messages.get(0))
                .build(), httpStatus);
    }
    
}
