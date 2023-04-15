package pl.com.web.shop.domain.specification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import pl.com.web.shop.domain.specification.model.SpecificationsApi;
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest;
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails;
import pl.com.web.shop.domain.specification.model.outside.SpecificationUpdateRequest;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class SpecificationController implements SpecificationsApi {
    private final SpecificationService specificationService;
    private final SpecificationMapper specificationMapper;

    @Override
    public ResponseEntity<SpecificationDetails> createSpecification(UUID itemId, SpecificationCreateRequest specificationCreateRequest) {
        log.info("Creating specification for item with id: {}", itemId);
        var dto = specificationMapper.createRequestDto(specificationCreateRequest);
        var details = specificationService.createSpecification(itemId, dto);
        log.debug("Created specification with id: {} for item with id: {}", itemId, details.getId());
        return ResponseEntity.ok(details);
    }

    @Override
    public ResponseEntity<SpecificationDetails> updateSpecification(UUID itemId, UUID specificationId, SpecificationUpdateRequest updateRequest) {
        log.info("Update specification with id: {} for item with id: {}", itemId, specificationId);
        var dto = specificationMapper.updateRequestDto(updateRequest);
        var details = specificationService.updateSpecification(itemId, specificationId, dto);
        log.debug("Updated specification with id: {} for item with id: {}", itemId, specificationId);
        return ResponseEntity.ok(details);
    }
}
