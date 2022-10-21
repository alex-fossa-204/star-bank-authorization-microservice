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
        summary = "[Password Recovery 1-st step] Check entered passport number.",
        method = "verifyUserPassport",
        requestBody = @RequestBody(
                description = "__[Passport verification request body]:__\n\n" +
                        " __(passportNumber)__ - represents passport number, which is used as credential."),
        description = "Check the user's passport for presence in the database as a registered user. A user is considered as registered if he has the parameters in the credential table.")
public @interface SwaggerOperationPassportVerificationPostRequest {
}
