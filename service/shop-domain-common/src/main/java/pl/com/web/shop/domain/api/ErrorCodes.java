package pl.com.web.shop.domain.api;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("PMD.SingularField")
public enum ErrorCodes {
    OBJECT_NOT_FOUND("OBJECT_NOT_FOUND"),
    VERSION_MISMATCH("VERSION_MISMATCH");

    @NotBlank
    private String value;

    ErrorCodes(@NotBlank String value) {
        this.value = value;
    }
}
