package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public final class Error {

    @NotBlank
    private final String code;

    @NotBlank
    private final String message;

    public Error(@JsonProperty("field") String code, @JsonProperty("message") String message) {
        this.code = code;
        this.message = message;
    }
}
