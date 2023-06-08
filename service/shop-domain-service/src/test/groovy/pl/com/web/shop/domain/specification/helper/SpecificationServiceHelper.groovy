package pl.com.web.shop.domain.specification.helper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.com.web.shop.domain.item.model.entity.Item
import pl.com.web.shop.domain.specification.model.entity.Specification
import pl.com.web.shop.domain.specification.repository.SpecificationRepository

@Service
class SpecificationServiceHelper {

    @Autowired
    private SpecificationRepository specificationRepository

    Specification saveSpecification(Item item, Map args = [:]) {
        saveSpecification(SpecificationHelper.specificationEntity(item, args))
    }

    Specification saveSpecification(Specification specification) {
        specification = specificationRepository.saveAndFlush(specification)

        getSpecification(specification.id)
    }

    Specification getSpecification(UUID id) {
        specificationRepository.getById(id)
    }
}
