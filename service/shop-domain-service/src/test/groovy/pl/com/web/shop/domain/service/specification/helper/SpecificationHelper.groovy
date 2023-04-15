package pl.com.web.shop.domain.service.specification.helper

import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.specification.model.entity.Specification
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails
import pl.com.web.shop.domain.specification.model.outside.SpecificationUpdateRequest

class SpecificationHelper {
    private static Map defaultSpecificationEntityArgs()
    {
        [
                name: "Memory",
                value: "512 GB"
        ]
    }

    static Specification specificationEntity(Item item, Map customArgs = [:]) {
        Map args = defaultSpecificationEntityArgs()
        args << customArgs

        Specification.builder()
            .name(args.name as String)
            .value(args.value as String)
            .item(item)
            .build()

    }

    static boolean compare(Specification entity, SpecificationCreateRequest request) {
        assert entity.name == request.name
        assert entity.value == request.value
        true
    }

    static boolean compare(Specification entity, SpecificationUpdateRequest request) {
        assert entity.version == request.version + 1
        assert entity.name == request.name
        assert entity.value == request.value
        true
    }

    static boolean compare(Specification entity, SpecificationDetails details) {
        assert entity.id
        assert entity.name == details.name
        assert entity.value == details.value
        true
    }
}
