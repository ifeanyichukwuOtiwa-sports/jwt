package iwo.wintech.jwt.main.security;

import iwo.wintech.jwt.api.JWTSubject;
import iwo.wintech.jwt.main.model.AccountUser;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ToString()
public class SecureUser implements UserDetails, JWTSubject {
    private final String username;
    private final String password;
    private final Set<String> roles;
    private final String name;
    private final UUID id;
    private final String email;
    private final Set<String> permissions;

    public SecureUser(final AccountUser user) {
        this.username = user.getUsername();
        this.password =  user.getPassword();
        this.roles = user.getRoles();
        this.name = user.getName();
        this.id = user.getId();
        this.email = user.getEmail();
        this.permissions = user.getUserPermissions();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Set<String> getRoles() {
        return new HashSet<>(this.roles);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
