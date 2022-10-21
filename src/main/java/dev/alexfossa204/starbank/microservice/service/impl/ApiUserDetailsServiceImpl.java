package dev.alexfossa204.starbank.microservice.service.impl;

import dev.alexfossa204.starbank.microservice.config.security.user.JwtUserDetails;
import dev.alexfossa204.starbank.microservice.mapper.impl.CredentialToJwtUserDetailsMapper;
import dev.alexfossa204.starbank.microservice.repository.model.Credential;
import dev.alexfossa204.starbank.microservice.repository.CredentialRepository;
import dev.alexfossa204.starbank.microservice.service.ApiUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static dev.alexfossa204.starbank.microservice.service.constant.ServiceExceptionConstant.*;

@Service
@Qualifier("userDetailsService")
@RequiredArgsConstructor
public class ApiUserDetailsServiceImpl implements ApiUserDetailsService {

    private final CredentialRepository credentialRepository;

    private final CredentialToJwtUserDetailsMapper credentialToJwtUserDetailsMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findCredentialByPhoneLoginOrPassportLogin(username, username)
                .orElseThrow(() -> new UsernameNotFoundException(CREDENTIAL_NOT_FOUND_MSG));
        JwtUserDetails jwtUserDetails = credentialToJwtUserDetailsMapper.mapEntityToDto(credential);
        jwtUserDetails.setUsername(username);
        return jwtUserDetails;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Credential loadCredentialByPassportIdentifier(String passportIdentifier) {
        return credentialRepository.findCredentialByPassportLogin(passportIdentifier).orElseThrow(() -> new UsernameNotFoundException(CREDENTIAL_NOT_FOUND_MSG));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Credential loadCredentialByPhoneIdentifier(String phoneIdentifier) {
        return credentialRepository.findCredentialByPhoneLogin(phoneIdentifier).orElseThrow(() -> new UsernameNotFoundException(CREDENTIAL_NOT_FOUND_MSG));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Credential updateCredential(Credential credential) {
        //Refactor!!!! (a.bakulin)
        return credentialRepository.save(credential);
    }
}
