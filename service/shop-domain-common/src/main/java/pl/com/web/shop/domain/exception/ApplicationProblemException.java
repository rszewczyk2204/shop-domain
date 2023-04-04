package pl.com.web.shop.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import pl.com.web.shop.domain.common.ProblemLogLevel;

@Data
public class ApplicationProblemException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final ProblemLogLevel logLevel;

    public ApplicationProblemException(String errorCode, HttpStatus httpStatus, ProblemLogLevel logLevel, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.logLevel = logLevel;
    }
}
