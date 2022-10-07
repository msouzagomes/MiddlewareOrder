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
        "listPrices",
        "salePrices"
})
public class FrigelarPf {

    @JsonProperty("listPrices")
    private Double listPrices;
    @JsonProperty("salePrices")
    private Double salePrices;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}