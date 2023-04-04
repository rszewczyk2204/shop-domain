package pl.com.web.shop.domain.item.repository;

import pl.com.web.shop.domain.item.model.entity.Item;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepositoryCustom {

    Optional<Item> findByIdAndDeletedIsFalse(@NotNull UUID id);
}
