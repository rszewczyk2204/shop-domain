package pl.com.web.shop.domain.item.helper

import org.springframework.stereotype.Service
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest

@Service
class ItemApiHelper {

    static ItemCreateRequest itemCreateRequest(Map customArgs = [:]) {
        ItemCreateRequest.builder()
            .name(customArgs.get("name") as String)
            .description(customArgs.get("description") as String)
            .available(customArgs.get("available") as Boolean)
            .price(customArgs.get("price") as Double)
            .mainItemId(customArgs.get("mainItemId") as UUID)
            .build()
    }

    static ItemUpdateRequest itemUpdateRequest(Map customArgs = [:]) {
        ItemUpdateRequest.builder()
            .id(customArgs.get("id") as UUID)
            .version(customArgs.get("version") as Integer)
            .name(customArgs.get("name") as String)
            .description(customArgs.get("description") as String)
            .available(customArgs.get("available") as Boolean)
            .price(customArgs.get("price") as Double)
            .build()
    }
}
