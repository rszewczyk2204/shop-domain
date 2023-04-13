package pl.com.web.shop.domain.item.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkItemRequestDto {

    @NotNull
    private Integer version;

    @NotNull
    private UUID id;
}
