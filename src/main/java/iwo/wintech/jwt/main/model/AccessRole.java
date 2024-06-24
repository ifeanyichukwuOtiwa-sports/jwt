package iwo.wintech.jwt.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessRole {
    public static final String SUPER_ADMIN = "superadmin";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID roleId = UUID.randomUUID();

    @Column(unique = true)
    private String role;

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER)
    private Set<AccessPermission> permissions;

    public boolean hasPermission(String permission) {
        return permissions.stream().anyMatch(p -> p.equals(new AccessPermission(permission)));
    }

    public Set<AccessPermission> getUserPermissions() {
        return new HashSet<>(permissions);
    }
}
