package iwo.wintech.jwt.api.dto;

import java.time.LocalDateTime;

public record TokenResponse(
        String token,
        LocalDateTime expiration
) {
}
