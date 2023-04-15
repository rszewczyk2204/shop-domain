package pl.com.web.shop.domain.specification.repository;

import pl.com.web.shop.domain.specification.model.entity.Specification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface SpecificationRepositoryCustom {

    Optional<Specification> findByIdAndGraphName(@NotNull UUID id, @NotBlank String graphName);
}
