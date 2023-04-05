package pl.com.web.shop.domain.common;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.com.web.shop.domain.exception.ApplicationProblemException;

@Order(100)
@RestControllerAdvice
public class RoutingAdvice extends Advice {

    @ExceptionHandler({ApplicationProblemException.class})
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, NativeWebRequest request) {
        return create(HttpStatus.NOT_FOUND, exception, request, ProblemLogLevel.WARN, error(ErrorCode.BAD_REQUEST.name()));
    }
}
