package pl.com.web.shop.domain.service.item.helper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.repository.ItemRepository

@Service
class ItemServiceHelper {

    @Autowired
    ItemRepository itemRepository

    Item getItem(UUID id) {
        itemRepository.get(id)
    }

    void cleanup() {
        itemRepository.deleteAll()
    }
}
