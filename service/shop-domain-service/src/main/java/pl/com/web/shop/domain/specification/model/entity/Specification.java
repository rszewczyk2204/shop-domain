package pl.com.web.shop.domain.specification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.com.bit.common.named.object.entity.NamedObjectSnap;
import pl.com.bit.common.versioned.entity.VersionedEntity;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.specification.model.dto.SpecificationCreateRequestDto;
import pl.com.web.shop.domain.specification.model.dto.SpecificationUpdateRequestDto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = Specification.SPECIFICATION_ENTITY_DETAILS,
                attributeNodes = {
                        @NamedAttributeNode("item")
                }
        )
})
@EqualsAndHashCode(callSuper = true)
public class Specification extends VersionedEntity {
    public static final String SPECIFICATION_ENTITY_DETAILS = "specification-entity-details";

    @NotBlank
    private String name;

    @NotBlank
    private String value;

    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static Specification of(@NotNull NamedObjectSnap user, @NotNull @Valid Item item, @NotNull @Valid SpecificationCreateRequestDto requestDto) {
        return Specification.builder()
                .name(requestDto.getName())
                .value(requestDto.getValue())
                .item(item)
                .author(user)
                .authorId(user.getCid())
                .build();
    }

    public void update(@NotNull NamedObjectSnap user, @NotNull @Valid SpecificationUpdateRequestDto requestDto) {
        setModifier(user);
        setName(requestDto.getName());
        setValue(requestDto.getValue());
    }
}
