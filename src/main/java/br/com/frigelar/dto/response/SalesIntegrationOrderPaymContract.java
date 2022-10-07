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
        "cardFirstDigits",
        "installmentNumber",
        "installmentValue",
        "installments",
        "paymMode",
        "paymSched",
        "payment",
        "paymentGateway",
        "paymentNSU",
        "paymentTID",
        "paymentTotalValue",
        "paymentType",
        "postProfile",
        "system",
        "transactionId"
})
public class SalesIntegrationOrderPaymContract {

    @JsonProperty("cardFirstDigits")
    private String cardFirstDigits;
    @JsonProperty("installmentNumber")
    private Integer installmentNumber;
    @JsonProperty("installmentValue")
    private Double installmentValue;
    @JsonProperty("installments")
    private Integer installments;
    @JsonProperty("paymMode")
    private String paymMode;
    @JsonProperty("paymSched")
    private String paymSched;
    @JsonProperty("payment")
    private String payment;
    @JsonProperty("paymentGateway")
    private String paymentGateway;
    @JsonProperty("paymentNSU")
    private String paymentNSU;
    @JsonProperty("paymentTID")
    private String paymentTID;
    @JsonProperty("paymentTotalValue")
    private Double paymentTotalValue;
    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("postProfile")
    private String postProfile;
    @JsonProperty("system")
    private String system;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
