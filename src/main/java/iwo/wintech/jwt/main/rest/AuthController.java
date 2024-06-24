package iwo.wintech.jwt.main.rest;

import iwo.wintech.jwt.dto.LoginRequest;
import iwo.wintech.jwt.dto.LoginResponse;
import iwo.wintech.jwt.dto.RegisterRequest;
import iwo.wintech.jwt.dto.RegisterResponse;
import iwo.wintech.jwt.main.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;


    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody final LoginRequest loginRequest) {
        return authService.authenticate(loginRequest);
    }

    @PostMapping(path = "/register")
    public RegisterResponse signup(@RequestBody final RegisterRequest request) {
        return authService.signup(request);
    }
}
