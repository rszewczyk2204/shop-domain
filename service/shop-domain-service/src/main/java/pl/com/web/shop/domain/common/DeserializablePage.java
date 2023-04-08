package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DeserializablePage<T> extends PageImpl<T> {
    protected Pageable pageable;

    @JsonCreator
    public DeserializablePage(@JsonProperty("content") List<T> content, @JsonProperty("pageable") DeserializablePageRequest pageable, @JsonProperty("totalElements") long total) {
        super(content, pageable, total);
        this.pageable = pageable;
    }

    public static <T> DeserializablePage<T> fromPage(Page<T> page) {
        return new DeserializablePage(page.getContent(), DeserializablePageRequest.fromPage(page), page.getTotalElements());
    }

    public Pageable getPageable() {
        return this.pageable;
    }
}
