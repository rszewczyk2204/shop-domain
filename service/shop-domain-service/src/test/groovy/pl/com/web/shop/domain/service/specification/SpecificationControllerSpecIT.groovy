package pl.com.web.shop.domain.service.specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.repository.ItemRepository
import pl.com.web.shop.domain.service.common.ShopRestSpecIT.ShopRestSpecIT
import pl.com.web.shop.domain.service.item.helper.ItemServiceHelper
import pl.com.web.shop.domain.service.specification.helper.SpecificationApiHelper
import pl.com.web.shop.domain.service.specification.helper.SpecificationHelper
import pl.com.web.shop.domain.service.specification.helper.SpecificationServiceHelper
import pl.com.web.shop.domain.specification.model.entity.Specification
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails
import pl.com.web.shop.domain.specification.model.outside.SpecificationUpdateRequest
import pl.com.web.shop.domain.specification.repository.SpecificationRepository

class SpecificationControllerSpecIT extends ShopRestSpecIT {
    static final String BASE_URL = "/shop-domain/items/{itemId}/specifications"
    static final String ID_URL = "${BASE_URL}/{specificationId}"

    @Autowired
    ItemRepository itemRepository

    @Autowired
    SpecificationRepository specificationRepository

    @Autowired
    ItemServiceHelper itemServiceHelper

    @Autowired
    SpecificationServiceHelper specificationServiceHelper

    void cleanup() {
        itemRepository.deleteAll()
        specificationRepository.deleteAll()
    }

    void "should create specification for an item"() {
        given:
            Item item = itemServiceHelper.saveItem()
            SpecificationCreateRequest request = SpecificationApiHelper.specificationCreateRequest()
        when:
            ResponseEntity<SpecificationDetails> response = httpPost(BASE_URL, request, SpecificationDetails, item.id)
        then:
            response.statusCode == HttpStatus.OK
            with(specificationRepository.getById(response.body.id)) {
                SpecificationHelper.compare(it, request)
                SpecificationHelper.compare(it, response.body)
            }
    }

    void "should update specification for an item"() {
        given:
            Item item = itemServiceHelper.saveItem()
            Specification specification = specificationServiceHelper.saveSpecification(item)
        and:
            SpecificationUpdateRequest request = SpecificationApiHelper.specificationUpdateRequest(version: item.version)
        when:
            ResponseEntity<SpecificationDetails> response = httpPut(ID_URL, request, SpecificationDetails, item.id, specification.id)
        then:
            response.statusCode == HttpStatus.OK
            with(specificationRepository.getById(specification.id)) {
                SpecificationHelper.compare(it, response.body)
                SpecificationHelper.compare(it, request)
            }
    }
}
