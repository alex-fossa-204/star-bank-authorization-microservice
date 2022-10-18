package dev.alexfossa204.starbank.authorization.controller;

import dev.alexfossa204.starbank.authorization.config.swagger.annotation.SwaggerAuthApiDocumentationConfig;
import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.service.exception.ServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping(value = {"/star-bank/auth", "/"})
@Tag(name = "Authorization management API", description = "This Component is responsible for user identification, authentication and authorization in Star Bank System")
public interface AuthApi {

    @SwaggerAuthApiDocumentationConfig
    @Operation(
            summary = "[Authorization 1-st step] Log in. Receive Json Web Token for having an access to Microservice Ecosystem features, according to user role",
            method = "authLoginUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "__[Login request body]:__\n\n" +
                            "__Header (Authorization-Method)__ - represents passport number / phone number, authorization method\n\n" +
                            "__(login)__ - represents login value (passport number / phone number), which is used for user identification into the System\n\n" +
                            "__(password)__ - represents password value, which is used for authorization process\n\n"),
            description = "Sends request to the server application. Creates an attempt to pass identification, authentication and authorization into the system")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<LoginResponseDto> authenticateUserPostRequest(@Parameter(name = "Password and login for authorization", required = true)
                                                                 @Valid @RequestBody LoginRequestDto body,
                                                                 @RequestHeader(name = "Authorization-Method") String authorizationMethod) throws ServiceException;

    @GetMapping("/validate")
    boolean validateToken(@RequestHeader("Authorization") String token);
}
