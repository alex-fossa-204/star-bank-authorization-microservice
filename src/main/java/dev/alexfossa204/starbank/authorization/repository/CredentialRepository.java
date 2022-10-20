package dev.alexfossa204.starbank.authorization.repository;

import dev.alexfossa204.starbank.authorization.repository.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {

    Optional<Credential> findCredentialByPhoneLoginOrPassportLogin(String phoneLogin, String passportLogin);

    Optional<Credential> findCredentialByPhoneLogin(String phoneLogin);

    Optional<Credential> findCredentialByPassportLogin(String passportLogin);

}
