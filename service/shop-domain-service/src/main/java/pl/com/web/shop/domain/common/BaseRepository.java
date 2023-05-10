package pl.com.web.shop.domain.common;

import pl.com.bit.common.exception.ElementVersionMismatchException;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public interface BaseRepository {

    default void checkVersion(@NotNull Integer entityVersion, @NotNull Integer requestVersion) {
        if (!Objects.equals(entityVersion, requestVersion)) {
            throw new ElementVersionMismatchException(String.format("The current version of the entity is: %s", entityVersion));
        }
    }
}
