package pl.com.web.shop.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.com.web.shop.domain.item.model.Item;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.repository.ItemRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Item createItem(@NotNull @Valid ItemCreateRequestDto requestDto) {
        Item item = Item.of(requestDto);
        return itemRepository.saveAndFlush(item);
    }
}
