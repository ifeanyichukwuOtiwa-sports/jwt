package iwo.wintech.jwt.main.service;

import iwo.wintech.jwt.api.JWTService;
import iwo.wintech.jwt.api.dto.TokenResponse;
import iwo.wintech.jwt.dto.LoginRequest;
import iwo.wintech.jwt.dto.LoginResponse;
import iwo.wintech.jwt.dto.RegisterRequest;
import iwo.wintech.jwt.dto.RegisterResponse;
import iwo.wintech.jwt.main.model.AccountUser;
import iwo.wintech.jwt.main.repo.AccountUserRepository;
import iwo.wintech.jwt.api.JWTSubject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManger;
    private final AccountUserRepository accountUserRepository;
    private final JWTService jwtService;
    private final Function<String, String> passwordEncode;

    @Override
    public LoginResponse authenticate(final LoginRequest loginRequest) {
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.userEmail(), loginRequest.password()
        );
        final Authentication authenticate = authenticationManger.authenticate(authToken);

        if (authenticate != null) {
            final JWTSubject accountUser = accountUserRepository.findAccountUserByEmail(loginRequest.userEmail())
                    .or(() -> accountUserRepository.findAccountUserByUsername(loginRequest.userEmail()))
                   .orElseThrow(() -> new RuntimeException("User not found"));

            final TokenResponse token = jwtService.createToken(accountUser);

            return new LoginResponse(
                    accountUser.getUsername(),
                    token.token(),
                    token.expiration()
            );
        }
        return null;
    }

    @Override
    public RegisterResponse signup(final RegisterRequest request) {
        final var saved = accountUserRepository.findAccountUserByEmail(request.userEmail())
                .orElseGet(()  -> {
                    final AccountUser user = AccountUser.builder()
                            .email(request.userEmail())
                            .username(request.userEmail())
                            .password(passwordEncode.apply(request.password()))
                            .name(request.name())
                            .phoneNumber(request.phoneNumber())
                            .roles(Set.of())
                            .build();
                    return accountUserRepository.save(user);
                });

        return new RegisterResponse(saved.getUsername(), saved.getEmail());
    }


}
