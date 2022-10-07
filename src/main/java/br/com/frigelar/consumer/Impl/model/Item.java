package br.com.frigelar.consumer.Impl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonPropertyOrder({
        "productId",
        "skuId",
        "priceListId",
        "listPrice"
})
public class Item {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("skuId")
    private String skuId;
    @JsonProperty("priceListId")
    private String priceListId;
    @JsonProperty("listPrice")
    private Double listPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}