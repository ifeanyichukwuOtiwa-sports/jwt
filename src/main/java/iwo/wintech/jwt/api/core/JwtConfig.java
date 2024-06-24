package iwo.wintech.jwt.api.core;

import io.jsonwebtoken.Jwts;
import iwo.wintech.jwt.api.JWTObjectParser;
import iwo.wintech.jwt.api.JWTService;
import iwo.wintech.jwt.api.JWTSubject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnClass({UserDetailsService.class, Jwts.class})
@ComponentScan(basePackages = { "iwo.wintech.jwt.api" })
@Configuration
public class JwtConfig {

    @Bean
    public JWTService jwtService(final JwtProperties properties, final JWTObjectParser<JWTSubject> parser) {
        return new JWTServiceImpl(properties, parser);
    }

    @Bean
    public JWTFilter jwtFilter(final JWTService jwtService, final UserDetailsService userDetailsService){
        return new JWTFilter(jwtService, userDetailsService);
    }
}
