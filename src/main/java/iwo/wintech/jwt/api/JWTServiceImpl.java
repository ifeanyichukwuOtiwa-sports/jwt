package iwo.wintech.jwt.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
class JWTServiceImpl implements JWTService {
    private final JwtProperties jwtProperties;

    @Override
    public String createToken(final String username) {
        return createFreshToken(username, jwtProperties.getExtraClaims());
    }

    private String createFreshToken(final String username, final Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getLifeTimeMillis()))
                .issuer(jwtProperties.getIssuer())
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey()), Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public String extractUserEmailFromToken(final String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
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
    public boolean isTokenValid(final String jwtToken, final String username) {
        final String name = extractClaims(jwtToken, Claims::getSubject);
        return Objects.equals(name, username) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(final String jwtToken) {
        final Date expirationDate = extractClaims(jwtToken, Claims::getExpiration);
        final Date now = new Date(System.currentTimeMillis());
        return expirationDate.before(now);
    }


}
