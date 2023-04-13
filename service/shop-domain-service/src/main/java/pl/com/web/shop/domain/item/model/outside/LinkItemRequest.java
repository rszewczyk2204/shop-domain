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
public class LinkItemRequest {

    @JsonProperty("version")
    @ApiModelProperty(required = true)
    private Integer version;

    @JsonProperty("id")
    @ApiModelProperty(required = true)
    private UUID id;
}
