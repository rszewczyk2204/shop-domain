package pl.com.web.shop.domain.common.ShopRestSpecIT

import pl.com.bit.common.named.object.entity.NamedObjectId
import pl.com.bit.common.named.object.entity.NamedObjectSnap


class NamedObjectSnapHelper {

    static NamedObjectSnap buildNamedObject(String type = 'TYPE', UUID id = UUID.randomUUID(), String name = "name") {
        NamedObjectSnap.builder()
            .cid(new NamedObjectId(id, type))
            .build()
    }
}
