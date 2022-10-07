package br.com.frigelar.dto.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "discountValue",
        "ean",
        "itemType",
        "name",
        "value",
        "finalValue",
        "productId",
        "qty",
        "skuId",
        "warehouseId",
        "weight"
})
public class SalesIntegrationOrderItemsContract {

    @JsonProperty("discountValue")
    private Double discountValue;
    @JsonProperty("ean")
    private String ean;
    @JsonProperty("itemType")
    private String itemType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private Double value;
    @JsonProperty("finalValue")
    private Double finalValue;
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("qty")
    private Double qty;
    @JsonProperty("skuId")
    private Integer skuId;
    @JsonProperty("warehouseId")
    private String warehouseId;
    @JsonProperty("weight")
    private Double weight;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
