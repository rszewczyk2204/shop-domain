package pl.com.web.shop.domain.specification.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants(asEnum = true)
public class SpecificationUpdateRequest {

    @JsonProperty("name")
    @ApiModelProperty(required = true)
    private String name;

    @JsonProperty("value")
    @ApiModelProperty(required = true)
    private String value;

    @JsonProperty("version")
    @ApiModelProperty(required = true)
    private Integer version;
}
