package pl.com.web.shop.domain.item.model.outside;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemLite {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("price")
    private double price;
}
