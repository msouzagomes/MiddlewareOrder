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
    private Double price;
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
