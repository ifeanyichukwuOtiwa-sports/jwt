package iwo.wintech.jwt.main.config;

import iwo.wintech.jwt.api.core.JwtConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "iwo.wintech.jwt.main")
@Import(JwtConfig.class)
@Configuration
@EnableJpaRepositories(basePackages = "iwo.wintech.jwt.main.repo")
@EntityScan(basePackages = "iwo.wintech.jwt.main.model")
public class JWTAppConfig {
}
