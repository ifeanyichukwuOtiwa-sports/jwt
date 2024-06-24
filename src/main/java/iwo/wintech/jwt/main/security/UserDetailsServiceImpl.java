package iwo.wintech.jwt.main.security;

import iwo.wintech.jwt.main.model.AccountUser;
import iwo.wintech.jwt.main.repo.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final AccountUser accountUserDao = userRepository.findAccountUserByEmail(username)
                .or(() -> userRepository.findAccountUserByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecureUser(accountUserDao);
    }
}
