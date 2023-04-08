package pl.com.web.shop.domain.common;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.com.web.shop.domain.item.model.outside.ItemsSearchFilter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@SuppressFBWarnings
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtils {

    public static PageRequest buildPageRequest(@NotNull ItemsSearchFilter filter) {
        return PageRequest.of(ObjectUtils.defaultIfNull(filter.getPage(), 0),
                ObjectUtils.defaultIfNull(filter.getSize(), 20), Sort.by(buildSortOrderList(filter.getSort()))
                        .and(Sort.by("id")));
    }

    private static List<Sort.Order> buildSortOrderList(@Nullable List<String> sortParams) {
        return CollectionUtils.emptyIfNull(sortParams).stream()
                .map(
                        param -> {
                            String[] sortParam = Optional.ofNullable(param).map(p -> p.split(",")).orElse(null);
                            assertOrderParameterIsCorrect(sortParam);
                            return new Sort.Order(Sort.Direction.fromString(sortParam[1]), sortParam[0]);
                        })
                .collect(Collectors.toList());
    }

    private static void assertOrderParameterIsCorrect(@Nullable String[] sortParam) {
        if (Objects.isNull(sortParam)) {
            throw new IllegalArgumentException("Null sort parameter");
        }
        if (sortParam.length != 2 || Sort.Direction.fromOptionalString(sortParam[1]).isEmpty()) {
            throw new IllegalArgumentException(
                    format("Illegal sort parameter: %s", Arrays.toString(sortParam)));
        }
    }
}
