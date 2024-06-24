package iwo.wintech.jwt.api;

import iwo.wintech.jwt.api.dto.TokenResponse;

import java.time.LocalDateTime;

public interface JWTService {
    TokenResponse createToken(JWTSubject username);
    JWTSubject extractUserEmailFromToken(String jwtToken);
    boolean isTokenValid(String jwtToken, JWTSubject username);
    LocalDateTime getExpiration(final String jwtToken);
}
