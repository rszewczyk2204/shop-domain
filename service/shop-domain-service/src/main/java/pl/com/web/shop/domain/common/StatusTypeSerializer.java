package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@NoArgsConstructor
public class StatusTypeSerializer extends JsonSerializer<HttpStatus> {

    @Override
    public void serialize(HttpStatus httpStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(httpStatus.value());
    }
}
