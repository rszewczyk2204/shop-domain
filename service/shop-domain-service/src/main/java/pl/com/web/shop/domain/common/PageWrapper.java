package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class PageWrapper<T> implements Page<T> {
    private final Page<T> delegate;

    public PageWrapper(@JsonProperty("content") List<T> content, @JsonProperty("page") int number, @JsonProperty("size") int size, @JsonProperty("totalElements") long totalElements) {
        this.delegate = new PageImpl<>(content, PageRequest.of(number, size), totalElements);
    }

    public PageWrapper() {
        this.delegate = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 0 ), 0);
    }

    @JsonProperty
    public int getTotalPages() {
        return this.delegate.getTotalPages();
    }

    @JsonProperty
    public long getTotalElements() {
        return this.delegate.getTotalElements();
    }

    @JsonProperty("page")
    public int getNumber() {
        return this.delegate.getNumber();
    }

    @JsonProperty
    public int getSize() {
        return this.delegate.getSize();
    }

    @JsonProperty
    public int getNumberOfElements() {
        return this.delegate.getNumberOfElements();
    }

    @JsonProperty
    public List<T> getContent() {
        return this.delegate.getContent();
    }

    @JsonProperty
    public boolean hasContent() {
        return this.delegate.hasContent();
    }

    @JsonIgnore
    public Sort getSort() {
        return this.delegate.getSort();
    }

    @JsonProperty
    public boolean isFirst() {
        return this.delegate.isFirst();
    }

    @JsonProperty
    public boolean isLast() {
        return this.delegate.isLast();
    }

    @JsonIgnore
    public boolean hasNext() {
        return this.delegate.hasNext();
    }

    @JsonIgnore
    public boolean hasPrevious() {
        return this.delegate.hasPrevious();
    }

    @JsonIgnore
    public Pageable nextPageable() {
        return this.delegate.nextPageable();
    }

    @JsonIgnore
    public Pageable previousPageable() {
        return this.delegate.previousPageable();
    }

    @JsonIgnore
    public <U> Page<U> map(Function<? super T, ? extends U> function) {
        return this.delegate.map(function);
    }

    @JsonIgnore
    public Iterator<T> iterator() {
        return this.delegate.iterator();
    }
}
