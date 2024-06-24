package iwo.wintech.jwt.main.service;

import iwo.wintech.jwt.main.model.AccessRole;

import java.util.List;
import java.util.UUID;

public interface AccessRoleService {
    AccessRole createNewRole(String role);
    void updateSuperAdminAccessRole(List<String> permissions);
    void linkUserAndRole(UUID userUuid, String role);
    void unLinkUserAndRole(UUID userUuid, String role);
}
