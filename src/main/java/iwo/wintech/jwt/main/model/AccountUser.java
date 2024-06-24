package iwo.wintech.jwt.main.model;

import iwo.wintech.jwt.api.JWTSubject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
@Table(name = "user")
@EqualsAndHashCode(exclude = "roles")
public class AccountUser implements JWTSubject {

    @Builder.Default
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column(unique = true, nullable = false)
    private String email;
    private String username;
    private String password;

    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<AccessRole> roles;

    @Override
    public Set<String> getRoles() {
        return roles.stream()
               .map(AccessRole::getRole)
               .collect(Collectors.toSet());
    }

    public Set<String> getUserPermissions() {
        return roles.stream()
               .flatMap(r -> r.getUserPermissions().stream())
               .map(AccessPermission::getPermission)
               .collect(Collectors.toSet());
    }
}
