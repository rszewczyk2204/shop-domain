package pl.com.web.shop.domain.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import pl.com.web.shop.domain.exception.ApplicationProblemException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.MoreObjects.firstNonNull;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class Advice {

    @Autowired(required = false)
    private ApplicationEventPublisher eventPublisher;

    @Autowired(required = false)
    private ProblemProperties problemProperties;

    @Autowired(required = false)
    private ProblemConverter problemConverter;

    protected final ResponseEntity<Object> create(HttpStatus status, Throwable exception, NativeWebRequest request, ProblemLogLevel logLevel, Error... errors) {
        return create(toProblem(status, exception, request, logLevel, errors), null);
    }

    protected final ResponseEntity<Object> create(HttpStatus status, Throwable exception, NativeWebRequest request, ProblemLogLevel logLevel) {
        return create(status, exception, request, null, logLevel);
    }

    protected final ResponseEntity<Object> create(HttpStatus status, Throwable exception, NativeWebRequest request, HttpHeaders headers, ProblemLogLevel logLevel) {
        return create(toProblem(status, exception, request, logLevel), headers);
    }

    private void publishEvent(Problem problem) {
        Optional.ofNullable(this.eventPublisher).ifPresent(publisher -> publisher.publishEvent(new ProblemOccurredEvent(problem)));
    }

    protected final ResponseEntity<Object> create(Problem problem, HttpHeaders headers) {
        publishEvent(problem);
        return ResponseEntity.status(getOrDefault(problem))
                .headers(headers)
                .body(nonNull(problemConverter) ? problemConverter.convert(problem) : problem);
    }

    private HttpStatus getOrDefault(Problem problem) {
        return ofNullable(problem.getStatus()).orElse(INTERNAL_SERVER_ERROR);
    }

    protected String getInstance(NativeWebRequest request) {
        return ofNullable(request.getNativeRequest(HttpServletRequest.class))
                .map(rq -> format("%s [%s]", rq.getRequestURI(), rq.getMethod()))
                .orElse(null);
    }

    protected String[] getStackTrace(Throwable exception) {
        return ofNullable(this.problemProperties).filter(ProblemProperties::isStackTraceEnabled)
                .map(properties -> ExceptionUtils.getRootCauseStackTrace(exception))
                .orElse(null);
    }

    protected Error error(String code) {
        return error(code, null);
    }

    protected Error error(String code, String message) {
        if (!StringUtils.hasText(code)) {
            return null;
        }
        if (isNull(message)) {
            message = code;
        }

        return Error.builder().code(code).message(message).build();
    }

    private Problem toProblem(HttpStatus status, Throwable exception, NativeWebRequest request, ProblemLogLevel logLevel, Error... errors) {

        HttpStatus httpStatus = status;
        ProblemLogLevel problemLogLevel = logLevel;
        List<Violation> violations = null;

        if(exception instanceof ApplicationProblemException) {
            httpStatus = firstNonNull(((ApplicationProblemException) exception).getHttpStatus(), httpStatus);
            problemLogLevel = firstNonNull(((ApplicationProblemException) exception).getLogLevel(), problemLogLevel);
        }

        return Problem.builder()
                .status(httpStatus)
                .detail(exception.getMessage())
                .stackTrace(getStackTrace(exception))
                .instance(getInstance(request))
                .violations(null)
                .logLevel(problemLogLevel)
                .errors(ofNullable(errors).filter(array -> array.length > 0).map(Arrays::asList).orElseGet(() -> {
                    if (exception instanceof ApplicationProblemException) {
                        String errorCode = ((ApplicationProblemException) exception).getErrorCode();
                        return Collections.singletonList(error(errorCode));
                    } else {
                        return Collections.emptyList();
                    }
                }))
                .build();
    }
}
