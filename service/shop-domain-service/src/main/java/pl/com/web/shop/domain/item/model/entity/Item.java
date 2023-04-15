package pl.com.web.shop.domain.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;
import pl.com.web.shop.domain.item.model.dto.ItemUpdateRequestDto;

import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
public class Item {
    public static final String ITEM_ENTITY_DETAILS = "item-entity-details";

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Version
    @NotNull
    @Min(0L)
    private Integer version;

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
