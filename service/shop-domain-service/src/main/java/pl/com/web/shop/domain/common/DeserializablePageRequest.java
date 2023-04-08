package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class DeserializablePageRequest extends PageRequest {
    public DeserializablePageRequest(int page, int size) {
        super(page, size, Sort.unsorted());
    }

    public DeserializablePageRequest(int page, int size, Sort.Direction direction, String... properties) {
        super(page, size, Sort.by(direction, properties));
    }

    @JsonCreator
    public DeserializablePageRequest(@JsonProperty("pageNumber") int page, @JsonProperty("pageSize") int size, @JsonProperty("sort") @JsonDeserialize(using = CustomSortDeserializer.class) Sort sort) {
        super(page, size, sort);
    }

    public static DeserializablePageRequest fromPageable(Pageable pageable) {
        return Optional.ofNullable(pageable).map((it) -> of(it.getPageNumber(), it.getPageSize(), it.getSort())).orElse((null));
    }

    public static DeserializablePageRequest fromPage(Page<?> page) {
        return Optional.ofNullable(page).map((it) -> of(it.getNumber(), it.getSize(), it.getSort())).orElse(null);
    }

    public static DeserializablePageRequest of(int page, int size) {
        return of(page, size, (Sort)null);
    }

    public static DeserializablePageRequest of(int page, int size, Sort sort) {
        return page >= 0 && size >= 1 ? new DeserializablePageRequest(page, size, sort) : null;
    }

}
