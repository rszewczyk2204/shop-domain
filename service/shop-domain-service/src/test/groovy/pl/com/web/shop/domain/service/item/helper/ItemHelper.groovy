package pl.com.web.shop.domain.service.item.helper

import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.model.outside.ItemDetails

class ItemHelper {

    static boolean compare(Item entity, ItemDetails details) {
        assert details.id
        assert entity.name == details.name
        assert entity.description == details.description
        assert entity.id == details.id
        assert entity.version == details.version
        assert entity.deleted == details.deleted
        true
    }
}
