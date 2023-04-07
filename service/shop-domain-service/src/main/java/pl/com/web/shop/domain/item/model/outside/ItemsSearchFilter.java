package pl.com.web.shop.domain.item.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Klasa do wyszukiwania obiektów")
public class ItemsSearchFilter {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("sort")
    private List<String> sort = null;

    @JsonProperty("nameContains")
    private String nameContains;

    @JsonProperty("descriptionContains")
    private String descriptionContains;
}
