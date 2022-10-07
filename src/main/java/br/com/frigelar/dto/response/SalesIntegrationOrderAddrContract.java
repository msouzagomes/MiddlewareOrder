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
        "city",
        "complement",
        "country",
        "isPrimary",
        "neighborhood",
        "number",
        "postalCode",
        "receiverName",
        "state",
        "street"
})
public class SalesIntegrationOrderAddrContract {

    @JsonProperty("city")
    private String city;
    @JsonProperty("complement")
    private String complement;
    @JsonProperty("country")
    private String country;
    @JsonProperty("isPrimary")
    private Boolean isPrimary;
    @JsonProperty("neighborhood")
    private String neighborhood;
    @JsonProperty("number")
    private String number;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("receiverName")
    private String receiverName;
    @JsonProperty("state")
    private String state;
    @JsonProperty("street")
    private String street;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
