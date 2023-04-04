package pl.com.web.shop.domain.service.item

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest
import pl.com.web.shop.domain.item.model.outside.ItemDetails
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest
import pl.com.web.shop.domain.item.repository.ItemRepository
import pl.com.web.shop.domain.service.common.ShopRestSpecIT.ShopRestSpecIT
import pl.com.web.shop.domain.service.item.helper.ItemApiHelper
import pl.com.web.shop.domain.service.item.helper.ItemHelper
import pl.com.web.shop.domain.service.item.helper.ItemServiceHelper

class ItemControllerSpecIT extends ShopRestSpecIT {

    @Autowired
    ItemRepository itemRepository

    @Autowired
    ItemServiceHelper itemServiceHelper

    void "should create item"() {
        given:
            ItemCreateRequest request = ItemApiHelper.itemCreateRequest(name: "test")
        when:
            ResponseEntity<ItemDetails> response = httpPost("/shop-domain/items", request, ItemDetails)
        then:
            response.statusCode == HttpStatus.OK
            with(itemServiceHelper.getItem(response.body.id)) {
                ItemHelper.compare(it, response.body)
            }
    }

    void "should update an item"() {
        given:
            Item item = itemServiceHelper.saveItem()
        and:
            ItemUpdateRequest request = ItemApiHelper.itemUpdateRequest(name: "test1", version: item.version, id: item.id)
        when:
            ResponseEntity<ItemDetails> response = httpPut("/shop-domain/items/{itemId}", request, ItemDetails, item.id)
        then:
            response.statusCode == HttpStatus.OK
            with(itemServiceHelper.getItem(response.body.id)) {
                ItemHelper.compare(it, response.body)
            }
    }
}
