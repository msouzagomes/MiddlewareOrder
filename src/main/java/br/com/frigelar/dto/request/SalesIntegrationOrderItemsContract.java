package br.com.frigelar.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer discountValue;
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
