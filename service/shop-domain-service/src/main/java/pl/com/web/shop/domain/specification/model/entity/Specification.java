package pl.com.web.shop.domain.specification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.com.web.shop.domain.item.model.entity.Item;
import pl.com.web.shop.domain.specification.model.dto.SpecificationCreateRequestDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = Specification.SPECIFICATION_ENTITY_DETAILS,
                attributeNodes = {
                        @NamedAttributeNode("item")
                }
        )
})
public class Specification {
    public static final String SPECIFICATION_ENTITY_DETAILS = "specification-entity-details";

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

    @NotBlank
    private String value;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static Specification of(@NotNull @Valid Item item, @NotNull @Valid SpecificationCreateRequestDto requestDto) {
        return Specification.builder()
                .name(requestDto.getName())
                .value(requestDto.getValue())
                .item(item)
                .build();
    }
}
