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
        "paymentType",
        "paymentGateway",
        "transactionId",
        "paymentSystem",
        "paymentTotalValue",
        "installments",
        "installmentNumber",
        "installmentValue",
        "cardLastDigits",
        "nsu",
        "tId"
})
public class Payment {

    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("paymentGateway")
    private String paymentGateway;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("paymentSystem")
    private String paymentSystem;
    @JsonProperty("paymentTotalValue")
    private Double paymentTotalValue;
    @JsonProperty("installments")
    private String installments;
    @JsonProperty("installmentNumber")
    private String installmentNumber;
    @JsonProperty("installmentValue")
    private Double installmentValue;
    @JsonProperty("cardLastDigits")
    private String cardLastDigits;
    @JsonProperty("nsu")
    private String nsu;
    @JsonProperty("tId")
    private String tId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}