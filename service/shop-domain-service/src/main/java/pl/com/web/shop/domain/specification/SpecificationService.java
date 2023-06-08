package pl.com.web.shop.domain.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.com.bit.common.named.object.entity.NamedObjectSnap;
import pl.com.bit.common.security.api.AccountIdentity;
import pl.com.bit.user.domain.user.api.snap.UserSnapService;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.repository.ItemRepository;
import pl.com.web.shop.domain.specification.model.dto.SpecificationCreateRequestDto;
import pl.com.web.shop.domain.specification.model.dto.SpecificationUpdateRequestDto;
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
    private final UserSnapService userService;

    @Transactional
    public SpecificationDetails createSpecification(@NotNull AccountIdentity user, @NotNull UUID itemId, @NotNull @Valid SpecificationCreateRequestDto requestDto) {
        NamedObjectSnap userSnap = userService.get(user.getId());

        Item item = itemRepository.get(itemId);
        Specification specification = Specification.of(userSnap, item, requestDto);
        return specificationMapper.specificationDetails(specificationRepository.saveAndFlush(specification));
    }

    public SpecificationDetails updateSpecification(@NotNull AccountIdentity user, @NotNull UUID itemId, @NotNull UUID specificationId, @NotNull @Valid SpecificationUpdateRequestDto requestDto) {
        NamedObjectSnap userSnap = userService.get(user.getId());

        Specification specification = specificationRepository.getById(specificationId);
        specificationRepository.checkVersion(specification.getVersion(), requestDto.getVersion());
        specification.update(userSnap, requestDto);
        return specificationMapper.specificationDetails(specificationRepository.saveAndFlush(specification));
    }
}
