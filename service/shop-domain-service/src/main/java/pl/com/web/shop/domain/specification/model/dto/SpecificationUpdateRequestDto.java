package pl.com.web.shop.domain.specification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationUpdateRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String value;

    @NotNull
    private Integer version;
}
