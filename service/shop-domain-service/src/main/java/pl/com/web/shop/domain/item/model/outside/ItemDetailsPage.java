package pl.com.web.shop.domain.item.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Strona obiektów")
public class ItemDetailsPage {

    @JsonProperty("first")
    private Boolean first;

    @JsonProperty("last")
    private Boolean last;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("numberOfElements")
    private Integer size;

    @JsonProperty("sort")
    private Object sort;

    @JsonProperty("totalElements")
    private Integer totalElements;

    @JsonProperty("totalPages")
    private Integer totalPages;

    @JsonProperty("content")
    private List<ItemDetails> content = new ArrayList<>();
}
