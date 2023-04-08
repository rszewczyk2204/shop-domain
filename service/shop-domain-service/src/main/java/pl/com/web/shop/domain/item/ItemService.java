package pl.com.web.shop.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.model.dto.ItemUpdateRequestDto;
import pl.com.web.shop.domain.item.model.outside.ItemsSearchFilter;
import pl.com.web.shop.domain.item.repository.ItemRepository;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.model.outside.ItemDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public ItemDetails createItem(@NotNull @Valid ItemCreateRequestDto requestDto) {
        Item item = Item.of(requestDto);
        return itemMapper.itemDetails(itemRepository.saveAndFlush(item));
    }

    @Transactional
    public ItemDetails updateItem(@NotNull UUID id, @NotNull @Valid ItemUpdateRequestDto requestDto) {
        Item item = itemRepository.get(id);
        itemRepository.checkVersion(item.getVersion(), requestDto.getVersion());
        item.update(requestDto);
        Item savedItem = itemRepository.saveAndFlush(item);
        return itemMapper.itemDetails(itemRepository.get(savedItem.getId()));
    }

    @Transactional(readOnly = true)
    public ItemDetails getItem(@NotNull UUID id) {
        Item item = itemRepository.get(id);
        return itemMapper.itemDetails(item);
    }

    @Transactional
    public void deleteItem(@NotNull UUID id) {
        itemRepository.softDeleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ItemDetails> findItems(@NotNull ItemsSearchFilter filter) {
        Page<Item> items = itemRepository.find(filter);
        return itemMapper.toPageItemDetails(items);
    }
}
