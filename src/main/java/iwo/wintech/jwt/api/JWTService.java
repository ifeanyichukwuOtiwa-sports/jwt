package iwo.wintech.jwt.api;

public interface JWTService {
    String createToken(String username);
    String extractUserEmailFromToken(String jwtToken);
    boolean isTokenValid(String jwtToken, String username);
}
