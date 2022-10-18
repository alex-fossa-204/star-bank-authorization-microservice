package dev.alexfossa204.starbank.authorization.controller.impl;

import dev.alexfossa204.starbank.authorization.controller.AuthApi;
import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.service.AuthenticationManagerService;
import dev.alexfossa204.starbank.authorization.service.exception.ServiceException;
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

    @Override
    public boolean validateToken(String token) {
        return authenticationManagerService.validate(token);
    }

}
