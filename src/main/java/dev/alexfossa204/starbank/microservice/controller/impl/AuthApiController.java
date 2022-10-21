package dev.alexfossa204.starbank.microservice.controller.impl;

import dev.alexfossa204.starbank.microservice.controller.AuthApi;
import dev.alexfossa204.starbank.microservice.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.microservice.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.microservice.service.AuthenticationManagerService;
import dev.alexfossa204.starbank.microservice.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

    private final AuthenticationManagerService authenticationManagerService;

    @Override
    public ResponseEntity<LoginResponseDto> authenticateUserPostRequest(LoginRequestDto body, String authorizationMethod) throws ServiceException {
        return new ResponseEntity<>(authenticationManagerService.doLogin(body, authorizationMethod), HttpStatus.OK);
    }

}
