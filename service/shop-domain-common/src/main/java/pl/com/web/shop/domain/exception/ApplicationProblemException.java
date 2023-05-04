package pl.com.web.shop.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import pl.com.web.shop.domain.common.ProblemLogLevel;

import javax.validation.constraints.NotNull;

@Data
public class ApplicationProblemException extends RuntimeException {
    @NotNull
    private final String errorCode;

    @NotNull
    private final HttpStatus httpStatus;

    @NotNull
    private final ProblemLogLevel logLevel;

    public ApplicationProblemException(@NotNull String errorCode, @NotNull HttpStatus httpStatus, @NotNull ProblemLogLevel logLevel, @NotNull String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.logLevel = logLevel;
    }
}
