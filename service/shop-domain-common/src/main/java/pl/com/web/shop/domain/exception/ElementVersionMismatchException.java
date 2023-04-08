package pl.com.web.shop.domain.exception;

import pl.com.web.shop.domain.exception.message.ErrorCodes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ElementVersionMismatchException extends RuntimeException {

    public ElementVersionMismatchException(@NotBlank String message) {
        super(message);
    }

    public ElementVersionMismatchException(@NotBlank String message, @NotNull Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return ErrorCodes.VERSION_MISMATCH.name();
    }
}
