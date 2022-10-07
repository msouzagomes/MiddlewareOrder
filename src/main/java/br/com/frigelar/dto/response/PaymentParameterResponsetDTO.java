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
        "occPaymentMethod",
        "paymentMethod",
        "formPayment",
        "invoiceLaunchProfile",
        "isActive",
        "paymentPlan"
})
public class PaymentParameterResponsetDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("occPaymentMethod")
    private String occPaymentMethod;
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    @JsonProperty("formPayment")
    private String formPayment;
    @JsonProperty("invoiceLaunchProfile")
    private String invoiceLaunchProfile;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonProperty("paymentPlan")
    private String paymentPlan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
