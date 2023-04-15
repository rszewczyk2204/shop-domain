package pl.com.web.shop.domain.config;

import org.hibernate.JDBCException;
import org.hibernate.TransactionException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import pl.com.web.shop.domain.common.Advice;
import pl.com.web.shop.domain.common.ProblemLogLevel;
import pl.com.web.shop.domain.exception.ElementVersionMismatchException;
import pl.com.web.shop.domain.exception.message.ErrorCodes;

import javax.persistence.RollbackException;

@Order(1)
@RestControllerAdvice
public class ShopDomainControllerAdvice extends Advice {

    @ExceptionHandler
    public ResponseEntity handle(RuntimeException exception, NativeWebRequest request) {
        return create(HttpStatus.NOT_FOUND, exception, request, ProblemLogLevel.TRACE);
    }

    @ExceptionHandler
    public ResponseEntity handle(ElementVersionMismatchException exception, NativeWebRequest request) {
        return create(HttpStatus.CONFLICT, exception, request, ProblemLogLevel.TRACE, error(ErrorCodes.VERSION_MISMATCH.name()));
    }

    @ExceptionHandler
    public ResponseEntity handle(EmptyResultDataAccessException exception, NativeWebRequest request) {
        return create(HttpStatus.BAD_REQUEST, exception, request, ProblemLogLevel.TRACE, error(ErrorCodes.OBJECT_NOT_FOUND.name()));
    }

    @ExceptionHandler
    public ResponseEntity handle(JDBCException exception, NativeWebRequest request) {
        return create(HttpStatus.INTERNAL_SERVER_ERROR, exception, request, ProblemLogLevel.ERROR, error(ErrorCodes.INTERNAL_COMMUNICATION_EXCEPTION.name()));
    }

    @ExceptionHandler
    public ResponseEntity handle(RollbackException exception, NativeWebRequest request) {
        return create(HttpStatus.INTERNAL_SERVER_ERROR, exception, request, ProblemLogLevel.ERROR, error(ErrorCodes.INTERNAL_COMMUNICATION_EXCEPTION.name()));
    }

    @ExceptionHandler
    public ResponseEntity handle(TransactionException exception, NativeWebRequest request) {
        return create(HttpStatus.INTERNAL_SERVER_ERROR, exception, request, ProblemLogLevel.ERROR, error(ErrorCodes.INTERNAL_COMMUNICATION_EXCEPTION.name()));
    }
}
