package iwo.wintech.jwt.api.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
        private int lifeTimeMillis;
        private String secretKey;
        private String issuer;
        private Map<String, Object> extraClaims = new HashMap<>();

    public byte [] getSecretKey() {
        return Base64.getEncoder().encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

}
