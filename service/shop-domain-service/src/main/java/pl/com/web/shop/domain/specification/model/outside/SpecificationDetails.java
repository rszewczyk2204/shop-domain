package pl.com.web.shop.domain.specification.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(description = "Specification details")
public class SpecificationDetails {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;
}
