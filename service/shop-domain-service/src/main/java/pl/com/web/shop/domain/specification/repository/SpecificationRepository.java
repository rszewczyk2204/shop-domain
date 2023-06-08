package pl.com.web.shop.domain.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.web.shop.domain.common.BaseRepository;
import pl.com.web.shop.domain.specification.model.entity.Specification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Repository
public interface SpecificationRepository extends BaseRepository, JpaRepository<Specification, UUID>, SpecificationRepositoryCustom {

    default Specification getById(@NotNull UUID id) {
        return getByIdAndGraphName(id, Specification.SPECIFICATION_ENTITY_DETAILS);
    }

    default Specification getByIdAndGraphName(@NotNull UUID id, @NotBlank String graphName) {
        return findByIdAndGraphName(id, graphName).orElseThrow();
    }
}
