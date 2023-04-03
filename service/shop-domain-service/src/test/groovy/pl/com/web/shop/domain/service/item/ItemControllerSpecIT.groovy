package pl.com.web.shop.domain.service.item

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.com.web.shop.domain.item.model.Item
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto
import pl.com.web.shop.domain.service.common.ShopRestSpecIT.ShopRestSpecIT

class ItemControllerSpecIT extends ShopRestSpecIT {


    void "should create item"() {
        given:
        ItemCreateRequestDto requestDto = new ItemCreateRequestDto()
        requestDto.name = "Test"
        when:
        ResponseEntity<Item> response = httpPost("/shop-domain/items", requestDto, Item)
        then:
        response.statusCode == HttpStatus.OK
    }
}
