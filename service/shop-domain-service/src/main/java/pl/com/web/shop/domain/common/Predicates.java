package pl.com.web.shop.domain.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Predicates {
    private final List<Predicate> predicates = new ArrayList<>();

    public static Predicates builder() {
        return new Predicates();
    }

    public Predicates containsIgnoreCase(StringPath path, String value) {
        return this.containsIgnoreCaseNoTrim(path, StringUtils.trim(value));
    }

    public Predicates containsIgnoreCaseNoTrim(StringPath path, String value) {
        this.addPredicate(path::containsIgnoreCase, value);
        return this;
    }

    public Predicate build() {
        return Optional.ofNullable(ExpressionUtils.allOf(this.predicates)).orElse(new BooleanBuilder());
    }

    private void addPredicate(Function<String, Predicate> expression, String value) {
        if (StringUtils.isNotBlank(value)) {
            this.predicates.add(expression.apply(value));
        }
    }
}
