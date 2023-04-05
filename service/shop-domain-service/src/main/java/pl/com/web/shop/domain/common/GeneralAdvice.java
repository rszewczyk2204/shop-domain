package pl.com.web.shop.domain.common;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

@Order(100)
@RestControllerAdvice
public class GeneralAdvice extends Advice {

    public ResponseEntity handleThrowable(Throwable exception, NativeWebRequest request) {
        return create(HttpStatus.INTERNAL_SERVER_ERROR, exception, request, ProblemLogLevel.WARN);
    }
}
