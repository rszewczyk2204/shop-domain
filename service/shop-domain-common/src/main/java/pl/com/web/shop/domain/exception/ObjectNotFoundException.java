package pl.com.web.shop.domain.exception;

import org.springframework.http.HttpStatus;
import pl.com.web.shop.domain.common.ProblemLogLevel;
import pl.com.web.shop.domain.exception.message.ErrorCodes;

import javax.validation.constraints.NotBlank;

public class ObjectNotFoundException extends ApplicationProblemException {

    public ObjectNotFoundException(@NotBlank String message) {
        super(ErrorCodes.OBJECT_NOT_FOUND.name(),
                HttpStatus.NOT_FOUND,
                ProblemLogLevel.WARN,
                message);
    }
}
