package pl.com.web.shop.domain.item.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.graph.GraphSemantic;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import pl.com.bit.common.querydsl.Predicates;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.model.entity.QItem;
import pl.com.web.shop.domain.item.model.outside.ItemsSearchFilter;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemRepositoryCustomImpl extends QuerydslRepositorySupport implements ItemRepositoryCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager entityManager) {
        super(QItem.class);
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Optional<Item> findByIdAndDeletedIsFalse(@NotNull UUID id, @NotBlank String graphName) {
        return Optional.ofNullable(queryFactory.select(QItem.item)
                .from(QItem.item)
                .where(QItem.item.id.eq(id).
                        and(QItem.item.deleted.isFalse()))
                .setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph(graphName))
                .fetchOne());
    }

    @Override
    public Optional<Item> findByIdAndDeletedIsTrue(UUID id) {
        return Optional.ofNullable(queryFactory.select(QItem.item)
                .from(QItem.item)
                .where(QItem.item.id.eq(id).and(QItem.item.deleted.isTrue()))
                .fetchOne());
    }

    @Override
    public Predicate buildPredicate(ItemsSearchFilter filter) {
        return Predicates.builder()
                .containsIgnoreCase(QItem.item.name, filter.getNameContains())
                .containsIgnoreCase(QItem.item.description, filter.getDescriptionContains())
                .build();
    }
}
