package dev.alexfossa204.starbank.authorization.repository;

import dev.alexfossa204.starbank.authorization.repository.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, UUID> {

    Optional<UserContact> findByPhoneNumber(String phoneNumber);
}
