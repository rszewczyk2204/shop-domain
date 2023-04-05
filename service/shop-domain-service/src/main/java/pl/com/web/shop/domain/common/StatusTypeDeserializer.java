package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@NoArgsConstructor
public class StatusTypeDeserializer extends JsonDeserializer<HttpStatus> {

    @Override
    public HttpStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int statusCode = jsonParser.getIntValue();
        return HttpStatus.valueOf(statusCode);
    }
}
