package pl.com.web.shop.domain.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorCodes {
    OBJECT_NOT_FOUND("OBJECT_NOT_FOUND"),
    VERSION_MISMATCH("VERSION_MISMATCH"),
    INVALID_ITEM("INVALID_ITEM"),
    INTERNAL_COMMUNICATION_EXCEPTION("INTERNAL_COMMUNICATION_EXCEPTION");

    @NotNull
    private final String value;
}
