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
        "Id",
        "customerGroup",
        "currencyCode",
        "fineCode",
        "interestCode",
        "country",
        "mainAddress",
        "mainContact"
})
public class ParametersClientRequestDTO {

    @JsonProperty("Id")
    private Object id;
    @JsonProperty("customerGroup")
    private String customerGroup;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("fineCode")
    private String fineCode;
    @JsonProperty("interestCode")
    private String interestCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("mainAddress")
    private String mainAddress;
    @JsonProperty("mainContact")
    private String mainContact;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
