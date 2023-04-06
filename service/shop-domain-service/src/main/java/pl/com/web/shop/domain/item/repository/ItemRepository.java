package pl.com.web.shop.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.com.web.shop.domain.exception.ObjectNotFoundException;
import pl.com.web.shop.domain.item.model.entity.Item;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    @Transactional(readOnly = true)
    default Item get(@NotNull UUID id) {
        return findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ObjectNotFoundException("Object with given identifier not found"));
    }

    @Transactional(readOnly = true)
    default Item getDeletedItem(@NotNull UUID id) {
        return findByIdAndDeletedIsTrue(id).orElseThrow(() -> new ObjectNotFoundException("Object with given identifier not found"));
    }

    @Modifying
    @Query("UPDATE Item i SET i.deleted = TRUE WHERE i.id = ?1")
    int softDeleteById(@NotNull UUID id);
}
