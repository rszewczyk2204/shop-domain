package pl.com.web.shop.domain.common.ShopRestSpecIT

import pl.com.bit.common.named.object.entity.NamedObjectId
import pl.com.bit.common.named.object.entity.NamedObjectSnap
import pl.com.bit.common.versioned.entity.VersionedEntity
import pl.com.web.shop.domain.common.RawVersionedEntity

import java.time.OffsetDateTime

import static pl.com.web.shop.domain.common.ShopRestSpecIT.NamedObjectSnapHelper.buildNamedObject

class VersionedEntityHelper {

    static Map defaultVersionedEntityArgs() {
        def author = buildNamedObject('USER')
        def modifier = buildNamedObject('USER')
        [
                id         : UUID.randomUUID(),
                version    : 0,
                created    : OffsetDateTime.now(),
                modified   : OffsetDateTime.now(),
                author     : author,
                authorId   : author.cid,
                modifier   : modifier,
                modifierId : modifier.cid
        ]
    }

    static VersionedEntity buildVersionedEntity(Map customArgs = [:]) {
        Map args = defaultVersionedEntityArgs()
        args << customArgs
        RawVersionedEntity.builder()
                .id(args.id as UUID)
                .version(args.version as Integer)
                .created(args.created as OffsetDateTime)
                .modified(args.modified as OffsetDateTime)
                .author(args.author as NamedObjectSnap)
                .authorId(args.authorId as NamedObjectId)
                .modifier(args.modifier as NamedObjectSnap)
                .modifierId(args.modifierId as NamedObjectId)
                .build()
    }
}
