package dev.alexfossa204.starbank.authorization.repository;

import dev.alexfossa204.starbank.authorization.repository.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
