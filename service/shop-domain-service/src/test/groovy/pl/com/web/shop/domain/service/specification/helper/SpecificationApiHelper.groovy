package pl.com.web.shop.domain.service.specification.helper

import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest

class SpecificationApiHelper {

    private static Map defaultSpecificationCreateRequestArgs()
    {
        [
                "name": "ram",
                "value": "8 GB",
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
}
