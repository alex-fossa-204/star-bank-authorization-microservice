package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.microservice.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.microservice.repository.model.Credential;

public interface AuthenticationService {

    LoginResponseDto login(LoginRequestDto body);

    boolean isAuthMethodSupported(String authMethod);

    Credential validateLoginAttempt(String targetLogin);

}
