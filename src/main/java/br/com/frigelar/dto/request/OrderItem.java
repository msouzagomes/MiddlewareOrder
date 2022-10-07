package br.com.frigelar.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productId",
        "itemValue",
        "quantity",
        "itemWarehouseId",
        "weight",
        "itemDiscountValue",
        "itemName",
        "skuId",
        "itemType"
})
public class OrderItem {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("itemValue")
    private Double itemValue;
    @JsonProperty("itemFinalValue")
    private Double itemFinalValue;
    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("itemWarehouseId")
    private String itemWarehouseId;
    @JsonProperty("weight")
    private Double weight;
    @JsonProperty("itemDiscountValue")
    private Double itemDiscountValue;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("skuId")
    private String skuId;
    @JsonProperty("itemType")
    private String itemType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}