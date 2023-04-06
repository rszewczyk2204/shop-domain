package pl.com.web.shop.domain.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import pl.com.web.shop.domain.item.model.ItemsApi;
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest;
import pl.com.web.shop.domain.item.model.outside.ItemDetails;
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ItemController implements ItemsApi {
    private final ItemService service;
    private final ItemMapper itemMapper;

    public ResponseEntity<ItemDetails> createItem(ItemCreateRequest request) {
        log.info("Creating an item");
        var dto = itemMapper.createRequestDto(request);
        var details = service.createItem(dto);
        log.debug(String.format("Created an item with given id, %s", details.getId()));
        return ResponseEntity.ok(details);
    }

    @Override
    public ResponseEntity<ItemDetails> updateItem(UUID itemId, ItemUpdateRequest request) {
        log.info("Updating an item");
        var dto = itemMapper.updateRequestDto(request);
        var details = service.updateItem(itemId, dto);
        log.debug(String.format("Updated an item with given id, %s", details.getId()));
        return ResponseEntity.ok(details);
    }

    @Override
    public ResponseEntity<ItemDetails> getItem(UUID itemId) {
        log.info("Getting an item");
        var details = service.getItem(itemId);
        log.debug(String.format("Got an item with given id, %s", details.getId()));
        return ResponseEntity.ok(details);
    }

    @Override
    public ResponseEntity<Void> deleteItem(UUID itemId) {
        log.info("Deleting an item");
        service.deleteItem(itemId);
        log.debug("Deleted an item");
        return ResponseEntity.noContent().build();
    }
}
