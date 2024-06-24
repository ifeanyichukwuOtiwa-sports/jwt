package iwo.wintech.jwt.dto;

public record LoginRequest(
        String userEmail,
        String password
) {
}
