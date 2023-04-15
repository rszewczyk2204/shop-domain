package pl.com.web.shop.domain.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.com.web.shop.domain.common.VersionedEntity;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.model.dto.ItemUpdateRequestDto;

import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = Item.ITEM_ENTITY_DETAILS,
                attributeNodes = {
                        @NamedAttributeNode("linkedItems")
                }
        )
})
@EqualsAndHashCode(callSuper = true)
public class Item extends VersionedEntity {
    public static final String ITEM_ENTITY_DETAILS = "item-entity-details";

    @NotBlank
    private String name;

    @Nullable
    private String description;

    private int quantity;

    private boolean deleted;

    private boolean available;

    private double price;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "item_linked_item",
        joinColumns = @JoinColumn(name = "linked_item_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> linkedItems = new HashSet<>();


    public static Item of(@NotNull ItemCreateRequestDto requestDto) {
        return Item.builder()
                .name(requestDto.getName())
                .available(requestDto.getAvailable())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .build();
    }

    public void update(@NotNull @Valid ItemUpdateRequestDto requestDto) {
        setName(requestDto.getName());
        setDescription(requestDto.getDescription());
    }

    public void linkItem(Item itemToLink) {
        linkedItems.add(itemToLink);
        itemToLink.linkedItems.add(this);
    }
}
