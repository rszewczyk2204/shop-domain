package pl.com.web.shop.domain.specification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import pl.com.web.shop.domain.specification.model.SpecificationsApi;
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest;
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class SpecificationController implements SpecificationsApi {
    private final SpecificationService specificationService;
    private final SpecificationMapper specificationMapper;

    @Override
    public ResponseEntity<SpecificationDetails> createItem(UUID itemId, SpecificationCreateRequest specificationCreateRequest) {
        log.info("Creating specification for item with id: {}", itemId);
        var dto = specificationMapper.createRequestDto(specificationCreateRequest);
        var details = specificationService.createSpecification(itemId, dto);
        log.debug("Created specification with id: {} for item with id: {}", itemId, details.getId());
        return ResponseEntity.ok(details);
    }
}
