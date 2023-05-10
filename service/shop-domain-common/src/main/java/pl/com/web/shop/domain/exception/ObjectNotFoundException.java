package pl.com.web.shop.domain.exception;

import org.springframework.http.HttpStatus;
import pl.com.bit.http.problem.ApplicationProblemException;
import pl.com.bit.http.problem.ProblemLogLevel;
import pl.com.web.shop.domain.api.ErrorCodes;

import javax.validation.constraints.NotBlank;

public class ObjectNotFoundException extends ApplicationProblemException {

    public ObjectNotFoundException(@NotBlank String msg) {
        super(ErrorCodes.OBJECT_NOT_FOUND.name(), HttpStatus.NOT_FOUND, ProblemLogLevel.WARN, msg);
    }
}
