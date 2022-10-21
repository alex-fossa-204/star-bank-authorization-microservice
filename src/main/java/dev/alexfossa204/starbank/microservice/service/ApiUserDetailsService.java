package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.repository.model.Credential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ApiUserDetailsService extends UserDetailsService {

    Credential loadCredentialByPassportIdentifier(String passportIdentifier);

    Credential loadCredentialByPhoneIdentifier(String phoneIdentifier);

    Credential updateCredential(Credential credential);
    
}
