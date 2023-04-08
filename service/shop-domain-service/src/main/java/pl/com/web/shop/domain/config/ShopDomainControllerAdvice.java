package pl.com.web.shop.domain.config;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import pl.com.web.shop.domain.common.Advice;
import pl.com.web.shop.domain.common.ProblemLogLevel;
import pl.com.web.shop.domain.exception.ElementVersionMismatchException;
import pl.com.web.shop.domain.exception.message.ErrorCodes;

@Order(1)
@RestControllerAdvice
public class ShopDomainControllerAdvice extends Advice {

    @ExceptionHandler
    public ResponseEntity handle(Exception exception, NativeWebRequest request) {
        return create(HttpStatus.NOT_FOUND, exception, request, ProblemLogLevel.TRACE);
    }

    @ExceptionHandler
    public ResponseEntity handle(ElementVersionMismatchException exception, NativeWebRequest request) {
        return create(HttpStatus.CONFLICT, exception, request, ProblemLogLevel.TRACE, error(ErrorCodes.VERSION_MISMATCH.name()));
    }
}
