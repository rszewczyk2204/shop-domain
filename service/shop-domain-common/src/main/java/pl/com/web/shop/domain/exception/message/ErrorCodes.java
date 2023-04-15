package pl.com.web.shop.domain.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorCodes {
    OBJECT_NOT_FOUND("OBJECT_NOT_FOUND"),
    VERSION_MISMATCH("VERSION_MISMATCH"),
    INTERNAL_COMMUNICATION_EXCEPTION("INTERNAL_COMMUNICATION_EXCEPTION");
    private final String value;
}
