package pl.com.web.shop.domain.item.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ApiModel(description = "Item details")
public class ItemDetails {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("price")
    private double price;

    @JsonProperty("linkedItems")
    private List<ItemLite> linkedItems = new ArrayList<>();
}
