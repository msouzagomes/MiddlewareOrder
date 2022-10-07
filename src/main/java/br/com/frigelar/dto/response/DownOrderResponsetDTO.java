package br.com.frigelar.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        "Id",
        "codeFilial",
        "taxDocumentType",
        "operatorTypeDf",
        "operatorTypePj",
        "operatorTypeInvoice"
})
public class DownOrderResponsetDTO {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("codeFilial")
    private String codeFilial;
    @JsonProperty("taxDocumentType")
    private String taxDocumentType;
    @JsonProperty("operatorTypeDf")
    private String operatorTypeDf;
    @JsonProperty("operatorTypePj")
    private String operatorTypePj;
    @JsonProperty("operatorTypeInvoice")
    private String operatorTypeInvoice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
