package br.com.frigelar.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cellPhone",
        "cnpjCpfNum",
        "currency",
        "custGroup",
        "email",
        "fineCode",
        "firstName",
        "id",
        "ieNum",
        "interestCode",
        "isCorporation",
        "isPrimary",
        "lastName",
        "phone",
        "tradeName"
})
public class SalesIntegrationOrderCustContract {

    @JsonProperty("cellPhone")
    private String cellPhone;
    @JsonProperty("cnpjCpfNum")
    private String cnpjCpfNum;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("custGroup")
    private String custGroup;
    @JsonProperty("email")
    private String email;
    @JsonProperty("fineCode")
    private String fineCode;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("ieNum")
    private String ieNum;
    @JsonProperty("interestCode")
    private String interestCode;
    @JsonProperty("isCorporation")
    private Boolean isCorporation;
    @JsonProperty("isPrimary")
    private Boolean isPrimary;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("tradeName")
    private String tradeName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
