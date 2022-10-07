package br.com.frigelar.dto.response;

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
        "jsonAif",
        "jsonOcc",
        "respAif",
        "statusEnv",
        "shippingGroupId"
})
public class SendOrderRequestDTO {

    @JsonProperty("jsonAif")
    private String jsonAif;
    @JsonProperty("jsonOcc")
    private String jsonOcc;
    @JsonProperty("respAif")
    private String respAif;
    @JsonProperty("statusEnv")
    private String statusEnv;
    @JsonProperty("shippingGroupId")
    private String shippingGroupId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
