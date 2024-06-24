package iwo.wintech.jwt.main.service;

import iwo.wintech.jwt.dto.LoginRequest;
import iwo.wintech.jwt.dto.LoginResponse;
import iwo.wintech.jwt.dto.RegisterRequest;
import iwo.wintech.jwt.dto.RegisterResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest loginRequest);
    RegisterResponse signup(RegisterRequest request);
}
