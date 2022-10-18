package dev.alexfossa204.starbank.authorization.service;

import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.repository.model.Credential;

public interface AuthenticationService {

    LoginResponseDto login(LoginRequestDto body);

    boolean isAuthMethodSupported(String authMethod);

    Credential validateLoginAttempt(String targetLogin);

}
