package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.Iterator;

public class CustomSortDeserializer extends JsonDeserializer<Sort> {

    public CustomSortDeserializer() {
    }

    public Sort deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ArrayNode node = (ArrayNode)jp.getCodec().readTree(jp);
        Sort.Order[] orders = new Sort.Order[node.size()];
        int i = 0;

        for(Iterator var6 = node.iterator(); var6.hasNext(); ++i) {
            JsonNode obj = (JsonNode)var6.next();
            orders[i] = new Sort.Order(Sort.Direction.valueOf(obj.get("direction").asText()), obj.get("property").asText());
        }

        return Sort.by(orders);
    }
}
