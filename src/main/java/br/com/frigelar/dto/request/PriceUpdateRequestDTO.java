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
        "Kit_id",
        "sku",
        "frigelarPf",
        "frigelarPj"
})
public class PriceUpdateRequestDTO {

    @JsonProperty("Kit_id")
    private String kitId;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("frigelarPf")
    private FrigelarPf frigelarPf;
    @JsonProperty("frigelarPj")
    private FrigelarPj frigelarPj;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}