package pl.com.web.shop.domain.item.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequestDto {

    @NotBlank
    private String name;

    @Nullable
    private String description;
}
