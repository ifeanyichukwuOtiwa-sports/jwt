package iwo.wintech.jwt.main.repo;

import iwo.wintech.jwt.main.model.AccessRole;
import iwo.wintech.jwt.main.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessRoleRepository extends JpaRepository<AccessRole, UUID> {
}
