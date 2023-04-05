package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public final class Violation {

    @NotBlank
    private final String field;

    @NotBlank
    private final String message;

    public Violation(@JsonProperty("field") String field, @JsonProperty("message") String message) {
        this.field = field;
        this.message = message;
    }
}
