package br.com.frigelar.dto.request;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "listPrices",
        "salePrices"
})
public class FrigelarPj {

    @JsonProperty("listPrices")
    private Double listPrices;
    @JsonProperty("salePrices")
    private Double salePrices;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}