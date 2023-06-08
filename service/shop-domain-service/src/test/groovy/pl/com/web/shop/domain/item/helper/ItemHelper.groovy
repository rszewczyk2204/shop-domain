package pl.com.web.shop.domain.item.helper

import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest
import pl.com.web.shop.domain.item.model.outside.ItemDetails
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest
import pl.com.web.shop.domain.item.model.outside.LinkItemRequest

import java.time.OffsetDateTime

import static pl.com.web.shop.domain.common.ShopRestSpecIT.VersionedEntityHelper.buildVersionedEntity


class ItemHelper {
    private static Map defaultItemEntityArgs() {
        [
                name: "Test",
                description: "Description",
                availabe: false,
                price: 1000.00,
                specification: null
        ]
    }

    static Item itemEntity(Map customArgs = [:]) {
        Map args = defaultItemEntityArgs()
        args << customArgs

        def item = Item.builder()
            .copyVersionedDataFrom(buildVersionedEntity(args))
            .id(UUID.randomUUID())
            .version(0)
            .created(OffsetDateTime.now())
            .modified(OffsetDateTime.now())
            .name(args.name as String)
            .description(args.description as String)
            .build()
        item
    }

    static boolean compare(Item entity, ItemCreateRequest request) {
        assert entity.id
        assert entity.name == request.name
        assert entity.description == request.description
        assert entity.available == entity.available
        assert entity.price == entity.price
        true
    }

    static boolean compare(Item entity, ItemUpdateRequest request) {
        assert entity.version == request.version + 1
        assert entity.name == request.name
        assert entity.description == request.description
        assert entity.available == request.available
        assert entity.price == request.price
        true
    }

    static boolean compare(Item entity, ItemDetails details) {
        assert details.id
        assert entity.name == details.name
        assert entity.description == details.description
        assert entity.id == details.id
        assert entity.version == details.version
        assert entity.deleted == details.deleted
        assert entity.available == details.available
        assert entity.price == details.price
        assert entity.quantity == details.quantity
        assert entity.linkedItems.size() == details.linkedItems.size()
        true
    }

    static boolean compare(Item entity, LinkItemRequest request) {
        assert entity.linkedItems*.id.contains(request.id)
        true
    }

    static boolean compareLists(List<Item> items, List<ItemDetails> itemDetails) {
        assert items.size() == itemDetails.size()
        [items.sort { it.id }, itemDetails.sort { it.id }].transpose().every {
            a, b -> compare((Item) a, (ItemDetails) b)
        }
    }
}
