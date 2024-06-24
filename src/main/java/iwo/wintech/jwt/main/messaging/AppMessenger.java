package iwo.wintech.jwt.main.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
@Component
public class AppMessenger {

    private final Function<AbstractAuthenticationEvent, ?> principal = e -> e.getAuthentication().getPrincipal();

    @EventListener
    public void handleAuthenticationSuccessEvent(final AuthenticationSuccessEvent event) {
        log.info("Authentication success: {}", principal.apply(event));

    }

    @EventListener
    public void handleAuthenticationFailureBadCredentialsEvent(final AuthenticationFailureBadCredentialsEvent event) {
        log.info("Authentication failed due to Wrong Credentials: {}", principal.apply(event));
    }
}
