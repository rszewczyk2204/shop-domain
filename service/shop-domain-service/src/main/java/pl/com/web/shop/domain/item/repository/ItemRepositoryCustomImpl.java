package pl.com.web.shop.domain.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import pl.com.web.shop.domain.item.model.entity.QItem;
import pl.com.web.shop.domain.item.model.entity.Item;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Optional<Item> findByIdAndDeletedIsFalse(UUID id) {
        return Optional.ofNullable(queryFactory.select(QItem.item)
                .from(QItem.item)
                .where(QItem.item.id.eq(id).and(QItem.item.deleted.isFalse()))
                .fetchOne());
    }

    @Override
    public Optional<Item> findByIdAndDeletedIsTrue(UUID id) {
        return Optional.ofNullable(queryFactory.select(QItem.item)
                .from(QItem.item)
                .where(QItem.item.id.eq(id).and(QItem.item.deleted.isTrue()))
                .fetchOne());
    }
}
