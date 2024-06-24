package iwo.wintech.jwt.main.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iwo.wintech.jwt.api.JWTObjectParser;
import iwo.wintech.jwt.api.JWTSubject;
import iwo.wintech.jwt.main.model.AccountUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class SecureUserParser implements JWTObjectParser<JWTSubject> {
    private final ObjectMapper mapper;
    @Override
    public String parseObjectToString(final JWTSubject obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JWTSubject parseStringToObject(final String str) {
        try {
            return mapper.readValue(str, AccountUser.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
