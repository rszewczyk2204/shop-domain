package pl.com.web.shop.domain.service.item.helper

import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.item.model.outside.ItemDetails

class ItemHelper {
    private static Map defaultItemEntityArgs() {
        [
                name: "Test",
                description: "Description"
        ]
    }

    static Item itemEntity(Map customArgs = [:]) {
        Map args = defaultItemEntityArgs()
        args << customArgs

        def item = Item.builder()
            .name(args.name as String)
            .description(args.description as String)
            .build()
        item
    }

    static boolean compare(Item entity, ItemDetails details) {
        assert details.id
        assert entity.name == details.name
        assert entity.description == details.description
        assert entity.id == details.id
        assert entity.version == details.version
        assert entity.deleted == details.deleted
        true
    }

    static boolean compareLists(List<Item> items, List<ItemDetails> itemDetails) {
        assert items.size() == itemDetails.size()
        [items.sort { it.id }, itemDetails.sort { it.id }].transpose().every {
            a, b -> compare((Item) a, (ItemDetails) b)
        }
    }
}
