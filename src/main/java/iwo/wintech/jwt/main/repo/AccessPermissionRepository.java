package iwo.wintech.jwt.main.repo;

import iwo.wintech.jwt.main.model.AccessPermission;
import iwo.wintech.jwt.main.model.AccessRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessPermissionRepository extends JpaRepository<AccessPermission, UUID> {
}
