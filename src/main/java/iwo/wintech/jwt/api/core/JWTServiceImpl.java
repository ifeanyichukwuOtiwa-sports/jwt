package iwo.wintech.jwt.api.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import iwo.wintech.jwt.api.JWTObjectParser;
import iwo.wintech.jwt.api.JWTService;
import iwo.wintech.jwt.api.JWTSubject;
import iwo.wintech.jwt.api.dto.TokenResponse;
import iwo.wintech.jwt.main.security.SecureUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
class JWTServiceImpl implements JWTService {
    private final JwtProperties jwtProperties;
    private final JWTObjectParser<JWTSubject> parser;

    @Override
    public TokenResponse createToken(final JWTSubject user) {
        final String subject = parser.parseObjectToString(user);
        return createFreshToken(subject, jwtProperties.getExtraClaims());
    }

    private TokenResponse createFreshToken(final String username, final Map<String, Object> extraClaims) {
        final Date exp = new Date(System.currentTimeMillis() + jwtProperties.getLifeTimeMillis());
        final String token = Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(exp)
                .issuer(jwtProperties.getIssuer())
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey()), Jwts.SIG.HS512)
                .compact();
        return new TokenResponse(token, convertInstantToLocalDateTime(exp.toInstant()));
    }

    @Override
    public JWTSubject extractUserEmailFromToken(final String jwtToken) {
        final String subject = extractClaims(jwtToken, Claims::getSubject);
        return parser.parseStringToObject(subject);
    }

    private <T> T extractClaims(final String jwtToken, final Function<Claims, T> getter) {
        final Claims claims = extractAllClaimsFromToken(jwtToken);
        return getter.apply(claims);
    }

    private Claims extractAllClaimsFromToken(final String jwtToken) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey()))
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    @Override
    public boolean isTokenValid(final String jwtToken, final JWTSubject userDetails) {
        final String authString = extractClaims(jwtToken, Claims::getSubject);
        final JWTSubject jwtSubject = parser.parseStringToObject(authString);
        return jwtSubject.canEqual(userDetails) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(final String jwtToken) {
        final Date expirationDate = extractClaims(jwtToken, Claims::getExpiration);
        final Date now = new Date(System.currentTimeMillis());
        return expirationDate.before(now);
    }

    public LocalDateTime getExpiration(final String jwtToken) {
        final Instant instant = extractClaims(jwtToken, Claims::getExpiration).toInstant();
        return convertInstantToLocalDateTime(instant);
    }

    private static LocalDateTime convertInstantToLocalDateTime(final Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


}
