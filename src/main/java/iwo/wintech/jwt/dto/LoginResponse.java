package iwo.wintech.jwt.dto;

import java.time.LocalDateTime;

public record LoginResponse(
        String email,
        String token,
        LocalDateTime expiration
) {
}
