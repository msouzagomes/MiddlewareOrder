package br.com.frigelar.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "locationId",
        "stockLevel",
        "stockLevelTotal"
})
public class CreateStockRequestDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("stockLevel")
    private String stockLevel;
    @JsonProperty("stockLevelTotal")
    private String stockLevelTotal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}