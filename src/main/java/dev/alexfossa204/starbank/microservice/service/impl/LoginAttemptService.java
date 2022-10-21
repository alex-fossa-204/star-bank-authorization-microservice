package dev.alexfossa204.starbank.microservice.service.impl;

import com.google.common.cache.LoadingCache;
import dev.alexfossa204.starbank.microservice.config.security.constant.JwtTokenConfigurationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class LoginAttemptService {

    private final LoadingCache<String, Integer> loginAttemptCache;

    public void evictUserFromLoginAttemptCache(String username) {
        loginAttemptCache.invalidate(username);
    }

    public void addUserToLoginAttemptCache(String username) {
        int attempts = 0;
        try {
            attempts = JwtTokenConfigurationConstant.LOGIN_ATTEMPT_INCREMENT + loginAttemptCache.get(username);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        loginAttemptCache.put(username, attempts);
    }

    public boolean hasExceededMaxAttempts(String username) {
        boolean isAttemptsExceeded = true;
        try {
            isAttemptsExceeded = loginAttemptCache.get(username) >= JwtTokenConfigurationConstant.MAXIMAL_LOGIN_ATTEMPT_QUANTITY;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return isAttemptsExceeded;
    }

}
