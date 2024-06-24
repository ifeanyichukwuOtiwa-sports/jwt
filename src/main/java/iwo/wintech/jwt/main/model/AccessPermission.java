package iwo.wintech.jwt.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "permission")
@Table(name = "role_permissions")
@Entity
public class AccessPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String permission;

    public AccessPermission(String permission) {
        this.permission = String.format("ROLE_%s",permission.toUpperCase());
    }
}
