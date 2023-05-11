package pl.com.web.shop.domain.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.com.bit.common.named.object.entity.NamedObjectSnap;
import pl.com.bit.common.versioned.entity.VersionedEntity;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.model.dto.ItemUpdateRequestDto;
import pl.com.web.shop.domain.specification.model.entity.Specification;

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
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
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
                        @NamedAttributeNode("linkedItems"),
                        @NamedAttributeNode("specifications")
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Specification> specifications = new HashSet<>();


    public static Item of(@NotNull NamedObjectSnap user, @Nullable @Valid Item mainItem, @NotNull @Valid ItemCreateRequestDto requestDto) {
        Item item = Item.builder()
                .name(requestDto.getName())
                .available(requestDto.getAvailable())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .author(user)
                .authorId(user.getCid())
                .build();
        if (Objects.nonNull(mainItem)) {
            item.getLinkedItems().add(mainItem);
        }
        return item;
    }

    public void update(@NotNull NamedObjectSnap user, @NotNull @Valid ItemUpdateRequestDto requestDto) {
        setName(requestDto.getName());
        setDescription(requestDto.getDescription());
        setAvailable(requestDto.getAvailable());
        setPrice(requestDto.getPrice());
        setModifier(user);
    }

    public void linkItem(@NotNull NamedObjectSnap user, @NotNull @Valid Item itemToLink) {
        setModifier(user);
        linkedItems.add(itemToLink);
    }
}
