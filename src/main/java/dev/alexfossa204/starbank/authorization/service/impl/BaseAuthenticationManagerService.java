package dev.alexfossa204.starbank.authorization.service.impl;

import dev.alexfossa204.starbank.authorization.config.security.provider.JwtTokenProvider;
import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.service.AuthenticationManagerService;
import dev.alexfossa204.starbank.authorization.service.AuthenticationService;
import dev.alexfossa204.starbank.authorization.service.exception.UnsupportedAuthorizationMethodException;
import dev.alexfossa204.starbank.authorization.config.security.constant.JwtTokenConfigurationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.*;

@RequiredArgsConstructor
@Service
public class BaseAuthenticationManagerService implements AuthenticationManagerService {
    private final JwtTokenProvider jwtTokenProvider;
    private final List<AuthenticationService> authenticationService;

    @Override
    public LoginResponseDto doLogin(LoginRequestDto body, String authorizationMethod) throws UnsupportedAuthorizationMethodException {
        Optional<AuthenticationService> serviceOptional = authenticationService
                .stream()
                .filter(serviceBeanCandidate -> serviceBeanCandidate.isAuthMethodSupported(authorizationMethod))
                .findAny();
        return serviceOptional
                .map(value -> value.login(body))
                .orElseThrow(() -> new UnsupportedAuthorizationMethodException(UNSUPPORTED_AUTHORIZATION_METHOD_MSG));
    }

    @Override
    public boolean validate(String authHeader) {
        String token = authHeader.substring(JwtTokenConfigurationConstant.TOKEN_PREFIX.length());
        String username = jwtTokenProvider.getSubjectUsername(token);
        return jwtTokenProvider.isTokenValid(username, token);
    }
}
