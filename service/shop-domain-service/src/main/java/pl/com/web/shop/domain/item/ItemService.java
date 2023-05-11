package pl.com.web.shop.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.com.bit.common.named.object.entity.NamedObjectSnap;
import pl.com.bit.common.security.api.AccountIdentity;
import pl.com.bit.user.domain.user.api.snap.UserSnapService;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.model.dto.ItemUpdateRequestDto;
import pl.com.web.shop.domain.item.model.dto.LinkItemRequestDto;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.item.model.outside.ItemDetails;
import pl.com.web.shop.domain.item.model.outside.ItemsSearchFilter;
import pl.com.web.shop.domain.item.repository.ItemRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserSnapService userService;

    @Transactional
    public ItemDetails createItem(@NotNull AccountIdentity user, @NotNull @Valid ItemCreateRequestDto requestDto) {
        NamedObjectSnap userSnap = userService.get(user.getId());

        Item mainItem = null;
        if (Objects.nonNull(requestDto.getMainItemId())) {
            mainItem = itemRepository.get(requestDto.getMainItemId());
        }
        Item item = Item.of(userSnap, mainItem, requestDto);
        item = itemRepository.saveAndFlush(item);
        return itemMapper.itemDetails(itemRepository.get(item.getId()));
    }

    @Transactional
    public ItemDetails updateItem(@NotNull AccountIdentity user, @NotNull UUID id, @NotNull @Valid ItemUpdateRequestDto requestDto) {
        NamedObjectSnap userSnap = userService.get(user.getId());

        Item item = itemRepository.get(id);
        itemRepository.checkVersion(item.getVersion(), requestDto.getVersion());
        item.update(userSnap, requestDto);
        Item savedItem = itemRepository.saveAndFlush(item);
        return itemMapper.itemDetails(itemRepository.get(savedItem.getId()));
    }

    @Transactional(readOnly = true)
    public ItemDetails getItem(@NotNull AccountIdentity user, @NotNull UUID id) {
        Item item = itemRepository.get(id);
        return itemMapper.itemDetails(item);
    }

    @Transactional
    public void deleteItem(@NotNull AccountIdentity user, @NotNull UUID id) {
        NamedObjectSnap userSnap = userService.get(user.getId());
        itemRepository.softDeleteById(userSnap, id);
    }

    @Transactional(readOnly = true)
    public Page<ItemDetails> findItems(@NotNull AccountIdentity user, @NotNull ItemsSearchFilter filter) {
        Page<Item> items = itemRepository.find(filter);
        return itemMapper.toPageItemDetails(items);
    }

    @Transactional
    public ItemDetails linkItem(@NotNull AccountIdentity user, @NotNull UUID itemId, @NotNull @Valid LinkItemRequestDto requestDto) {
        NamedObjectSnap userSnap = userService.get(user.getId());

        Item item = itemRepository.get(itemId);
        itemRepository.checkVersion(item.getVersion(), requestDto.getVersion());
        item.linkItem(userSnap, itemRepository.get(requestDto.getId()));
        Item savedItem = itemRepository.saveAndFlush(item);
        return itemMapper.itemDetails(savedItem);
    }
}
