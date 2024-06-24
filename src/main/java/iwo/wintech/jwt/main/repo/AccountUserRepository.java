package iwo.wintech.jwt.main.repo;

import iwo.wintech.jwt.main.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountUserRepository extends JpaRepository<AccountUser, UUID> {

    Optional<AccountUser> findAccountUserByEmail(String email);
    Optional<AccountUser> findAccountUserByUsername(String username);
}
