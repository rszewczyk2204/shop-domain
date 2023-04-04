package pl.com.web.shop.domain.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.web.shop.domain.item.model.dto.ItemCreateRequestDto;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {

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

    public static Item of(@NotNull ItemCreateRequestDto requestDto) {
        return Item.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();
    }
}
