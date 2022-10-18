package dev.alexfossa204.starbank.authorization.service.impl;

import dev.alexfossa204.starbank.authorization.config.security.provider.JwtTokenProvider;
import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.repository.model.Credential;
import dev.alexfossa204.starbank.authorization.service.ApiUserDetailsService;
import dev.alexfossa204.starbank.authorization.service.AuthenticationService;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static dev.alexfossa204.starbank.authorization.service.impl.AbstractAuthenticationService.AbstractAuthenticationServiceConstant.*;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractAuthenticationService implements AuthenticationService {

    protected final ApiUserDetailsService userDetailsService;

    protected final AuthenticationManager authenticationManager;

    protected final JwtTokenProvider jwtTokenProvider;

    protected final LoginAttemptService loginAttemptService;

    @Override
    public LoginResponseDto login(LoginRequestDto body) {
        Credential credential = validateLoginAttempt(body.getLogin());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getLogin(), body.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(body.getLogin());
        String token = jwtTokenProvider.generateToken(userDetails);
        return LoginResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .message(LOGGED_IN_SUCCESSFULLY_MESSAGE)
                .jwtToken(token)
                .role(Sets.newHashSet(credential.getUser().getRole().getRoleName()))
                .build();
    }

    protected Credential validateNonLockedCredential(Credential credential, String userLogin) {
        boolean isNonLockedCredential = credential.getIsNonLocked();
        if(isNonLockedCredential) {
            boolean isLoginAttemptsExceeded = loginAttemptService.hasExceededMaxAttempts(userLogin);
            if(isLoginAttemptsExceeded) {
                credential.setIsNonLocked(false);
            }
        }
        if (!isNonLockedCredential) {
            loginAttemptService.evictUserFromLoginAttemptCache(userLogin);
        }
        credential.setLastLoginDate(new Date());
        userDetailsService.updateCredential(credential);
        return credential;
    }

    interface AbstractAuthenticationServiceConstant {

        String LOGGED_IN_SUCCESSFULLY_MESSAGE = "Logged in successfully";

    }

}
