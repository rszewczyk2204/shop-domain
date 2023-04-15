package pl.com.web.shop.domain.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.repository.ItemRepository;
import pl.com.web.shop.domain.specification.model.dto.SpecificationCreateRequestDto;
import pl.com.web.shop.domain.specification.model.entity.Specification;
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails;
import pl.com.web.shop.domain.specification.repository.SpecificationRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationMapper specificationMapper;
    private final SpecificationRepository specificationRepository;
    private final ItemRepository itemRepository;

    public SpecificationDetails createSpecification(@NotNull UUID itemId, @NotNull @Valid SpecificationCreateRequestDto requestDto) {
        Item item = itemRepository.get(itemId);
        Specification specification = Specification.of(item, requestDto);
        return specificationMapper.specificationDetails(specificationRepository.saveAndFlush(specification));
    }
}
