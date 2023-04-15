package pl.com.web.shop.domain.service.specification.helper

import pl.com.web.shop.domain.specification.model.entity.Specification
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails

class SpecificationHelper {

    static boolean compare(Specification entity, SpecificationCreateRequest request) {
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
