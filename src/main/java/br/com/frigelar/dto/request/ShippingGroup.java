package br.com.frigelar.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "shippingGroupId",
        "warehouseId",
        "deliveryType",
        "shippingValue",
        "shippingEstimateDate",
        "shippingEstimate",
        "courierCnpj",
        "courierName",
        "deliveryContactName",
        "deliveryContactPhone",
        "shippingGroupsItems",
        "shippingAddressPostalCode",
        "shippingAddressStreet",
        "shippingAddressNumber",
        "shippingAddressComplement",
        "shippingAddressNeighborhood",
        "shippingAddressCity",
        "shippingAddressState",
        "shippingAddressCountry",
        "shippingAddressEmail",
        "shippingAddressReceiverName",
        "shippingAddressReceiverCellPhone"
})
public class ShippingGroup {

    @JsonProperty("shippingGroupId")
    private String shippingGroupId;
    @JsonProperty("warehouseId")
    private String warehouseId;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("shippingValue")
    private Double shippingValue;
    @JsonProperty("shippingEstimateDate")
    private String shippingEstimateDate;
    @JsonProperty("shippingEstimate")
    private String shippingEstimate;
    @JsonProperty("courierCnpj")
    private String courierCnpj;
    @JsonProperty("courierName")
    private String courierName;
    @JsonProperty("deliveryContactName")
    private String deliveryContactName;
    @JsonProperty("deliveryContactPhone")
    private String deliveryContactPhone;
    @JsonProperty("shippingGroupsItems")
    private List<ShippingGroupsItem> shippingGroupsItems = new ArrayList<ShippingGroupsItem>();
    @JsonProperty("shippingAddressPostalCode")
    private String shippingAddressPostalCode;
    @JsonProperty("shippingAddressStreet")
    private String shippingAddressStreet;
    @JsonProperty("shippingAddressNumber")
    private String shippingAddressNumber;
    @JsonProperty("shippingAddressComplement")
    private String shippingAddressComplement;
    @JsonProperty("shippingAddressNeighborhood")
    private String shippingAddressNeighborhood;
    @JsonProperty("shippingAddressCity")
    private String shippingAddressCity;
    @JsonProperty("shippingAddressState")
    private String shippingAddressState;
    @JsonProperty("shippingAddressCountry")
    private String shippingAddressCountry;
    @JsonProperty("shippingAddressEmail")
    private String shippingAddressEmail;
    @JsonProperty("shippingAddressReceiverName")
    private String shippingAddressReceiverName;
    @JsonProperty("shippingAddressReceiverCellPhone")
    private String shippingAddressReceiverCellPhone;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}