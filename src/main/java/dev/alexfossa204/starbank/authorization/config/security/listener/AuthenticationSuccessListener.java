package dev.alexfossa204.starbank.authorization.config.security.listener;

import dev.alexfossa204.starbank.authorization.service.impl.LoginAttemptService;
import dev.alexfossa204.starbank.authorization.config.security.user.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessListener {

    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        JwtUserDetails user = (JwtUserDetails) event.getAuthentication().getPrincipal();
        loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
    }

}
