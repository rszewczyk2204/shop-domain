package pl.com.web.shop.domain.specification.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.graph.GraphSemantic;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import pl.com.web.shop.domain.specification.model.entity.QSpecification;
import pl.com.web.shop.domain.specification.model.entity.Specification;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SpecificationRepositoryCustomImpl extends QuerydslRepositorySupport implements SpecificationRepositoryCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public SpecificationRepositoryCustomImpl(@NotNull EntityManager entityManager) {
        super(QSpecification.class);
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Optional<Specification> findByIdAndGraphName(UUID id, String graphName) {
        return Optional.ofNullable(queryFactory.select(QSpecification.specification)
                .from(QSpecification.specification)
                .where(QSpecification.specification.id.eq(id))
                .setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph(graphName))
                .fetchOne());
    }
}
