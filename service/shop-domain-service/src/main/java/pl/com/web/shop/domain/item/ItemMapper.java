package pl.com.web.shop.domain.item;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.model.dto.ItemUpdateRequestDto;
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest;
import pl.com.web.shop.domain.item.model.outside.ItemDetails;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    builder = @Builder(disableBuilder = true))
public interface ItemMapper {

    ItemCreateRequestDto createRequestDto(ItemCreateRequest createRequest);

    ItemDetails itemDetails(Item item);

    ItemUpdateRequestDto updateRequestDto(ItemUpdateRequest updateRequest);
}
