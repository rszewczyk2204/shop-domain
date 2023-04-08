package pl.com.web.shop.domain.item.repository;

import com.querydsl.core.types.Predicate;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.model.outside.ItemsSearchFilter;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepositoryCustom {

    Optional<Item> findByIdAndDeletedIsFalse(@NotNull UUID id);

    Optional<Item> findByIdAndDeletedIsTrue(@NotNull UUID id);

    Predicate buildPredicate(@NotNull ItemsSearchFilter filter);
}
