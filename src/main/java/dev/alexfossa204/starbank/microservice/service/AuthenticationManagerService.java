package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.microservice.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.microservice.service.exception.ServiceException;

public interface AuthenticationManagerService {

    LoginResponseDto doLogin(LoginRequestDto body, String authorizationMethod) throws ServiceException;

    boolean validate(String authHeader);

}
