package pl.com.web.shop.domain.service.item.helper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.repository.ItemRepository

@Service
class ItemServiceHelper {

    @Autowired
    ItemRepository itemRepository

    Item saveItem(Map args = [:]) {
        saveItem(ItemHelper.itemEntity(args))
    }

    Item saveItem(Item item) {
        item = itemRepository.saveAndFlush(item)

        getItem(item.id)
    }

    Item getItem(UUID id) {
        itemRepository.get(id)
    }

    Item getDeletedItem(UUID id) {
        itemRepository.getDeletedItem(id)
    }
}
