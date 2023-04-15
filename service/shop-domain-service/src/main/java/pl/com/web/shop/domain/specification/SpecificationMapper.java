package pl.com.web.shop.domain.specification;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.com.web.shop.domain.specification.model.dto.SpecificationCreateRequestDto;
import pl.com.web.shop.domain.specification.model.entity.Specification;
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest;
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    builder = @Builder(disableBuilder = true))
public interface SpecificationMapper {

    SpecificationCreateRequestDto createRequestDto(SpecificationCreateRequest createRequest);

    SpecificationDetails specificationDetails(Specification specification);
}
