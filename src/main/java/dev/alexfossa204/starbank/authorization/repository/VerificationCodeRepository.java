package dev.alexfossa204.starbank.authorization.repository;

import dev.alexfossa204.starbank.authorization.repository.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, UUID> {

    Optional<VerificationCode> findVerificationCodeByUuid(UUID uuid);

    Optional<VerificationCode> findVerificationCodeByCodeValueAndAndPhoneNumber(String codeValue, String phoneNumber);

}
