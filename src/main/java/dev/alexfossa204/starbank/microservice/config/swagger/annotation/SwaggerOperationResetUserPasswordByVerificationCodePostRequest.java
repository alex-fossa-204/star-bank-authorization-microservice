package dev.alexfossa204.starbank.microservice.config.swagger.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "[Password Recovery 4-th step] Reset user password, using generated verification code",
        method = "resetUserPasswordByVerificationCode",
        requestBody = @RequestBody(
                description = "__[Password recovery request body]:__\n\n" +
                        "__(passportNumber)__ - represents passport number, which is used as credential.\n\n" +
                        "__(newPassword)__ - represents new user password value.\n\n" +
                        "__(confirmPassword)__ - represents new user password value, required to confirm.\n\n" +
                        "__(verificationCode)__ - represents generated verification code, which is required for password recovery.\n\n"),
        description = "Reset user password and set user account active if it was blocked. To receive code - use Verification Code management API endpoints.")
public @interface SwaggerOperationResetUserPasswordByVerificationCodePostRequest {
}
