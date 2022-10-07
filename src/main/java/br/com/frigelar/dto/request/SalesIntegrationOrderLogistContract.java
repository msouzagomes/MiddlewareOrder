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
        "carrierCNPJ",
        "carrierName",
        "price",
        "selectedSLA",
        "shippingAddressReceiverCellPhone",
        "shippingAddressReceiverName",
        "shippingEstimate",
        "shippingEstimatedDate",
        "warehouseId"
})
public class SalesIntegrationOrderLogistContract {

    @JsonProperty("carrierCNPJ")
    private String carrierCNPJ;
    @JsonProperty("carrierName")
    private String carrierName;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("selectedSLA")
    private String selectedSLA;
    @JsonProperty("shippingAddressReceiverCellPhone")
    private String shippingAddressReceiverCellPhone;
    @JsonProperty("shippingAddressReceiverName")
    private String shippingAddressReceiverName;
    @JsonProperty("shippingEstimate")
    private String shippingEstimate;
    @JsonProperty("shippingEstimatedDate")
    private String shippingEstimatedDate;
    @JsonProperty("warehouseId")
    private String warehouseId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
