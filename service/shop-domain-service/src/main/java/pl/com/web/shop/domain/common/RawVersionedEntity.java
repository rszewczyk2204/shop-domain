package pl.com.web.shop.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.com.bit.common.versioned.entity.VersionedEntity;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RawVersionedEntity extends VersionedEntity {
}
