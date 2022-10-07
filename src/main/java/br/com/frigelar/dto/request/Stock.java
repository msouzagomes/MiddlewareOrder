package br.com.frigelar.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "locationId",
        "stockLevel",
        "stockLevelTotal"
})
public class Stock {

    @JsonProperty("id")
    private String id;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("stockLevel")
    private Integer stockLevel;
    @JsonProperty("stockLevelTotal")
    private StockLevelTotal stockLevelTotal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
