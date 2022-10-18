package dev.alexfossa204.starbank.authorization.service;

import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.service.exception.ServiceException;

public interface AuthenticationManagerService {

    LoginResponseDto doLogin(LoginRequestDto body, String authorizationMethod) throws ServiceException;

    boolean validate(String authHeader);

}
