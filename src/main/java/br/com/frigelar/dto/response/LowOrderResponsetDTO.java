package br.com.frigelar.dto.response;

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
        "id",
        "typeOrderSale",
        "budget",
        "languageUsedErp",
        "reserveItemsAutomatically",
        "productUser",
        "fineCode",
        "interestCode",
        "typeSale",
        "codeSeller",
        "presenceType"
})
public class LowOrderResponsetDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("typeOrderSale")
    private String typeOrderSale;
    @JsonProperty("budget")
    private String budget;
    @JsonProperty("languageUsedErp")
    private String languageUsedErp;
    @JsonProperty("reserveItemsAutomatically")
    private String reserveItemsAutomatically;
    @JsonProperty("productUser")
    private String productUser;
    @JsonProperty("fineCode")
    private String fineCode;
    @JsonProperty("interestCode")
    private String interestCode;
    @JsonProperty("typeSale")
    private String typeSale;
    @JsonProperty("codeSeller")
    private String codeSeller;
    @JsonProperty("presenceType")
    private String presenceType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
