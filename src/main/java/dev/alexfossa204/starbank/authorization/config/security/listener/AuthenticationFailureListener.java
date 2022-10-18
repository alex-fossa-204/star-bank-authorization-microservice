package dev.alexfossa204.starbank.authorization.config.security.listener;

import dev.alexfossa204.starbank.authorization.service.impl.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {

    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        String userName = (String) event.getAuthentication().getPrincipal();
        loginAttemptService.addUserToLoginAttemptCache(userName);

    }

}
