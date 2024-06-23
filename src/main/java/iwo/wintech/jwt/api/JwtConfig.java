package iwo.wintech.jwt.api;

import io.jsonwebtoken.Jwts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnClass({UserDetailsService.class, Jwts.class})
public class JwtConfig {

    @Bean
    public JWTService jwtService(final JwtProperties properties) {
        return new JWTServiceImpl(properties);
    }

    @Bean
    public JWTFilter jwtFilter(final JWTService jwtService, final UserDetailsService userDetailsService){
        return new JWTFilter(jwtService, userDetailsService);
    }
}
