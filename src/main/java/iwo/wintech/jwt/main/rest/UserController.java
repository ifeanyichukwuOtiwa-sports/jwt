package iwo.wintech.jwt.main.rest;

import iwo.wintech.jwt.main.model.AccountUser;
import iwo.wintech.jwt.main.repo.AccountUserRepository;
import iwo.wintech.jwt.api.JWTSubject;
import iwo.wintech.jwt.main.security.SecureUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
@RestController
public class UserController {
    private final AccountUserRepository accountUserRepository;

    @GetMapping("/me")
    public ResponseEntity<JWTSubject> me() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final JWTSubject user = (SecureUser) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }


    @GetMapping("/all")
    public ResponseEntity<List<? extends JWTSubject>> getAllUser() {
        final List<AccountUser> users = accountUserRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
