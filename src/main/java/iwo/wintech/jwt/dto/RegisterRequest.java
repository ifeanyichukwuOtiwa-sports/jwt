package iwo.wintech.jwt.dto;

public record RegisterRequest(
        String userEmail,
        String password,
        String name,
        String phoneNumber
) {
}
