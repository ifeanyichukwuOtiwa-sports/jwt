### ***ABOUT***
A simple library which provides the necessary JWT configuration for securing your Spring boot API with JWT.


###### Provided configuration:
1. Authentication: creates jwt token with user-details, secret-key, expiration time and issuer (using a JWTService class)
2. Authorization: validates token for expiration and any related issue (using a JWTFilter class)

### ***USAGE***
1. Open terminal
2. Clone repo: `git clone https://github.com/ifeanyichukwuOtiwa-sports/jwt.git`
3. Enter and hit `cd jwt`
4. Enter and hit `./gradlew clean build publishMavenJavaPublicationToMavenLocal`
5. Declare the library as a dependency in your pom.xml or build.gradle (using the above group id, artifact id and version).
```build.gradle
implementation 'iwo.wintech:jwt:0.0.1-SNAPSHOT'
```
or
```pom.xml
<dependency>
      <groupId>iwo.wintech</groupId>
      <artifactId>jwt</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
```

1. Add this to your MAIN Configuration class (above @Configuration): `@Import(JWTConfig.class)`
2. Declare values for these properties: `jwt.secretKey`, `jwt.lifeTimeMillis`, and `jwt.issuer` in your application.properties file.

### ***EXAMPLE***

```java
@Import(JWTConfig.class)
@Configuration
public class AppMainConfiguration {
    
}

@Service
@RequiredArgsConstructor
public class AuthService {
        
        private final JWTService jwtService;

        public ResponseEntity<LoginResponse> authenticate(LoginRequest request){

                Authentication auth = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
                if( auth != null ){
                    AccountUser user = userRepository.findByUsername(request.getUsername());
                    String token = jwtService.createToken(user);
                    return new ResponseEntity<>(LoginResponse.builder().user(user).token(token).build(), HttpStatus.OK);
                }
                return null;
        }
}   


@Configuration
public class SecurityConfig {
    

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity customSecurity, final JWTFilter jwtFilter) throws Exception{

        return customSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requestRegistry -> requestRegistry
                    .requestMatchers("/api/users/register", "/api/users/login", "/path/to/ignore/..").permitAll()
                    .requestMatchers("/api/users/all").hasAnyAuthority(Role.ADMIN.name())
                    .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```


**NOTE**:
1. Only suitable for learning environments (until It is hosted on Maven Central).

Modified version of [From divad1998](https://github.com/divad1998/jwt)] 


Example with old version from [@tericcabrel](https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac)