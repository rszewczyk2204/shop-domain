package pl.com.web.shop.domain.specification.helper

import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest
import pl.com.web.shop.domain.specification.model.outside.SpecificationUpdateRequest

class SpecificationApiHelper {

    private static Map defaultSpecificationCreateRequestArgs()
    {
        [
                "name": "ram",
                "value": "8 GB",
        ]
    }

    private static Map defaultSpecificationUpdateRequestArgs() {
        [
                "name": "ROM",
                "value": "16GB"
        ]
    }

    static SpecificationCreateRequest specificationCreateRequest(Map customArgs = [:]) {
        Map args = defaultSpecificationCreateRequestArgs()
        args << customArgs

        new SpecificationCreateRequest(
                args[(SpecificationCreateRequest.Fields.name.name())] as String,
                args[(SpecificationCreateRequest.Fields.value.name())] as String,
        )
    }

    static SpecificationUpdateRequest specificationUpdateRequest(Map customArgs = [:]) {
        Map args = defaultSpecificationUpdateRequestArgs()
        args << customArgs

        new SpecificationUpdateRequest(
                args[(SpecificationUpdateRequest.Fields.name.name())] as String,
                args[(SpecificationUpdateRequest.Fields.value.name())] as String,
                args[(SpecificationUpdateRequest.Fields.version.name())] as Integer
        )
    }
}
