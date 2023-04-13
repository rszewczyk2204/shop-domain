package pl.com.web.shop.domain.service.item

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.com.web.shop.domain.common.Problem
import pl.com.web.shop.domain.exception.message.ErrorCodes
import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest
import pl.com.web.shop.domain.item.model.outside.ItemDetails
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest
import pl.com.web.shop.domain.item.model.outside.ItemsSearchFilter
import pl.com.web.shop.domain.item.model.outside.LinkItemRequest
import pl.com.web.shop.domain.item.repository.ItemRepository
import pl.com.web.shop.domain.service.common.ShopRestSpecIT.ShopRestSpecIT
import pl.com.web.shop.domain.service.item.helper.ItemApiHelper
import pl.com.web.shop.domain.service.item.helper.ItemHelper
import pl.com.web.shop.domain.service.item.helper.ItemServiceHelper

class ItemControllerSpecIT extends ShopRestSpecIT {

    static final String BASE_URL = "/shop-domain/items"
    static final String ID_URL = "${BASE_URL}/{itemId}"
    static final String SEARCH_URL = "${BASE_URL}/search"
    static final String LINK_URL = "${ID_URL}/link"

    @Autowired
    ItemRepository itemRepository

    @Autowired
    ItemServiceHelper itemServiceHelper

    void cleanup() {
        itemRepository.deleteAll()
    }

    void "should create item"() {
        given:
            ItemCreateRequest request = ItemApiHelper.itemCreateRequest(name: "test", available: true, price: 1000.00)
        when:
            ResponseEntity<ItemDetails> response = httpPost(BASE_URL, request, ItemDetails)
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
            ItemUpdateRequest request = ItemApiHelper.itemUpdateRequest(name: "test1", version: item.version, id: item.id, available: false, price: 50)
        when:
            ResponseEntity<ItemDetails> response = httpPut(ID_URL, request, ItemDetails, item.id)
        then:
            response.statusCode == HttpStatus.OK
            with(itemServiceHelper.getItem(response.body.id)) {
                ItemHelper.compare(it, response.body)
            }
    }

    void "should throw an exception when item's version differs from update's version"() {
        given:
            Item item = itemServiceHelper.saveItem()
        and:
            ItemUpdateRequest request = ItemApiHelper.itemUpdateRequest(name: "test1", version: 10, id: item.id, price: 1000, available: true)
        when:
            ResponseEntity<Problem> response = httpPut(ID_URL, request, Problem, item.id)
        then:
            response.statusCode == HttpStatus.CONFLICT
            response.body.errors*.code == [ErrorCodes.VERSION_MISMATCH.name()]
    }

    void "should get an item"() {
        given:
            Item item = itemServiceHelper.saveItem()
        when:
            ResponseEntity<ItemDetails> response = httpGet(ID_URL, ItemDetails, item.id)
        then:
            response.statusCode == HttpStatus.OK
            ItemHelper.compare(item, response.body)
    }

    void "should throw an exception on item not found"() {
        given:
            Item item = itemServiceHelper.saveItem()
        when:
            ResponseEntity<Problem> response = httpGet(ID_URL, Problem, UUID.randomUUID())
        then:
            response.statusCode == HttpStatus.NOT_FOUND
            response.body.errors*.code == [ErrorCodes.OBJECT_NOT_FOUND.name()]
    }

    void "should delete an item"() {
        given:
            Item item = itemServiceHelper.saveItem()
        when:
            ResponseEntity<Void> response = httpDelete(ID_URL, Void, item.id)
        then:
            response.statusCode == HttpStatus.NO_CONTENT
            with(itemServiceHelper.getDeletedItem(item.id)) {
                it.deleted
            }
    }

    void "should find items"() {
        given:
            Item item1 = itemServiceHelper.saveItem(name: "test1", description: "description1")
            Item item2 = itemServiceHelper.saveItem(name: "test2", description: "description2")
        and:
            ItemsSearchFilter filter = ItemsSearchFilter.builder()
                .nameContains("test")
                .build()
        when:
            def responseType = new ParameterizedTypeReference<Page<ItemDetails>>() {}
            def response = httpPost(SEARCH_URL, filter, responseType)
        then:
            response.statusCode == HttpStatus.OK
            response.body.content.size() == 2
            ItemHelper.compareLists([item1, item2] as List, new ArrayList(response.body.content))
        when:
            filter = ItemsSearchFilter.builder()
                .nameContains("test1")
                .build()
            response = httpPost(SEARCH_URL, filter, responseType)
        then:
            response.statusCode == HttpStatus.OK
            response.body.content.size() == 1
            ItemHelper.compare(item1, response.body.content.first())
        when:
            filter = ItemsSearchFilter.builder()
                    .descriptionContains("description")
                    .build()
            response = httpPost(SEARCH_URL, filter, responseType)
        then:
            response.statusCode == HttpStatus.OK
            response.body.content.size() == 2
            ItemHelper.compareLists([item1, item2] as List, new ArrayList(response.body.content))
        when:
            filter = ItemsSearchFilter.builder()
                    .descriptionContains("description1")
                    .build()
            response = httpPost(SEARCH_URL, filter, responseType)
        then:
            response.statusCode == HttpStatus.OK
            response.body.content.size() == 1
            ItemHelper.compare(item1, response.body.content.first())
        when:
            filter = ItemsSearchFilter.builder()
                    .nameContains("test3")
                    .build()
            response = httpPost(SEARCH_URL, filter, responseType)
        then:
            response.statusCode == HttpStatus.OK
            response.body.content.size() == 0
    }

    void "should link two items"() {
        given:
            Item item1 = itemServiceHelper.saveItem()
            Item item2 = itemServiceHelper.saveItem()
        and:
            def request = LinkItemRequest.builder()
                .id(item2.id)
                .version(item1.version)
                .build()
        when:
            ResponseEntity<ItemDetails> response = httpPost(LINK_URL, request, ItemDetails, item1.id)
        then:
            response.statusCode == HttpStatus.OK
            with(itemRepository.get(item1.id)) {
                ItemHelper.compare(it, response.body)
                ItemHelper.compare(it, request)
            }
    }
}
