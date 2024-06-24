package iwo.wintech.jwt.main.starter;

import iwo.wintech.jwt.main.config.JWTAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JWTAppConfig.class)
public class JWTApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }
}
