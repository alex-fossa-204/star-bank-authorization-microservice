package dev.alexfossa204.starbank.authorization.service.impl;

import dev.alexfossa204.starbank.authorization.config.security.provider.JwtTokenProvider;
import dev.alexfossa204.starbank.authorization.repository.model.Credential;
import dev.alexfossa204.starbank.authorization.service.ApiUserDetailsService;
import dev.alexfossa204.starbank.authorization.service.AuthenticationService;
import dev.alexfossa204.starbank.authorization.service.constant.AuthenticationServiceConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@Qualifier("passportAuthenticationService")
public class PassportAuthenticationService extends AbstractAuthenticationService implements AuthenticationService {

    public PassportAuthenticationService(ApiUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, LoginAttemptService loginAttemptService) {
        super(userDetailsService, authenticationManager, jwtTokenProvider, loginAttemptService);
    }

    @Override
    public boolean isAuthMethodSupported(String authMethod) {
        return StringUtils.equals(authMethod, AuthenticationServiceConstant.AUTH_TYPE_PASSPORT);
    }

    @Override
    public Credential validateLoginAttempt(String targetLogin) {
        Credential credential = userDetailsService.loadCredentialByPassportIdentifier(targetLogin);
        validateNonLockedCredential(credential, targetLogin);
        return credential;
    }
}
