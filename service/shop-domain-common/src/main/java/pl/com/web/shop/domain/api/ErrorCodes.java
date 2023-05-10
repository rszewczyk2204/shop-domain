package pl.com.web.shop.domain.api;

public enum ErrorCodes {
    OBJECT_NOT_FOUND("OBJECT_NOT_FOUND"),
    VERSION_MISMATCH("VERSION_MISMATCH");
    private String value;

    ErrorCodes(String value) {
        this.value = value;
    }
}
