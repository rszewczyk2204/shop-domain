package pl.com.web.shop.domain.item.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequest {

    @JsonProperty("id")
    @ApiModelProperty(required = true)
    private UUID id;

    @JsonProperty("version")
    @ApiModelProperty(required = true)
    private Integer version;

    @JsonProperty("name")
    @ApiModelProperty(required = true)
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("available")
    @ApiModelProperty(required = true)
    private Boolean available;

    @JsonProperty("price")
    @ApiModelProperty(required = true)
    private Double price;

    @JsonProperty("specification")
    private String specification;
}
