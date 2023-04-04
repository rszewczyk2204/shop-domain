package pl.com.web.shop.domain.service.item.helper

import org.springframework.stereotype.Service
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest

@Service
class ItemApiHelper {

    static ItemCreateRequest itemCreateRequest(Map customArgs = [:]) {
        ItemCreateRequest.builder()
            .name(customArgs.get("name") as String)
            .description(customArgs.get("description") as String)
            .build()
    }

    static ItemUpdateRequest itemUpdateRequest(Map customArgs = [:]) {
        ItemUpdateRequest.builder()
            .id(customArgs.get("id") as UUID)
            .version(customArgs.get("version") as Integer)
            .name(customArgs.get("name") as String)
            .description(customArgs.get("description") as String)
            .build()
    }
}
