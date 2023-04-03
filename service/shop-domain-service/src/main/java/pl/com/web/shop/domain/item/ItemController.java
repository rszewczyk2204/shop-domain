package pl.com.web.shop.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.web.shop.domain.item.model.Item;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;

    @RequestMapping(value = "/shop-domain/items", produces = {"application/json"}, consumes = {"application/json"}, method = {RequestMethod.POST})
    public ResponseEntity<Item> createItem(@RequestBody @NotNull @Valid ItemCreateRequestDto requestDto) {
        Item item = service.createItem(requestDto);
        return ResponseEntity.ok(item);
    }
}
