package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public final class Problem {

    @NotNull
    @JsonSerialize(
            using = StatusTypeSerializer.class
    )
    @JsonDeserialize(
            using = StatusTypeDeserializer.class
    )
    private final HttpStatus status;

    private final String detail;

    private final String instance;

    private final String[] stackTrace;

    private final List<Violation> violations;

    private final List<Error> errors;

    private final ProblemLogLevel logLevel;

    public Problem(@JsonProperty("status") HttpStatus status, @JsonProperty("detail") String detail, @JsonProperty("instance") String instance, @JsonProperty("stackTrace") String[] stackTrace, @JsonProperty("violations") List<Violation> violations, @JsonProperty("errors") List<Error> errors, @JsonProperty("logLevel") ProblemLogLevel logLevel) {
        this.status = status;
        this.detail = detail;
        this.instance = instance;
        this.stackTrace = stackTrace;
        this.violations = violations;
        this.errors = errors;
        this.logLevel = logLevel;
    }
}
