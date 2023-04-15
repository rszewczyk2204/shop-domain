package pl.com.web.shop.domain.specification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationCreateRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String value;
}
