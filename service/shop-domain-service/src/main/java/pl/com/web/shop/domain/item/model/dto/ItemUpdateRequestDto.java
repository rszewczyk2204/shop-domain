package pl.com.web.shop.domain.item.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequestDto {

    @NotNull
    private UUID id;

    @NotNull
    private Integer version;

    @NotBlank
    private String name;

    @Nullable
    private String description;

    @NotNull
    private Boolean available;

    @NotNull
    private Double price;
}
