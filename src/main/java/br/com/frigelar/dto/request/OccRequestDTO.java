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
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orderId",
        "itemsValue",
        "discountValue",
        "shippingValue",
        "orderObservation",
        "totalValue",
        "orderStatus",
        "orderCreationDateTime",
        "installationPartnerId",
        "orderItems",
        "customerIdOcc",
        "payment",
        "vendorId",
        "shippingGroups"
})
public class OccRequestDTO {

    private Long reprocessOrderId;
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("itemsValue")
    private Double itemsValue;
    @JsonProperty("discountValue")
    private Integer discountValue;
    @JsonProperty("shippingValue")
    private Double shippingValue;
    @JsonProperty("orderObservation")
    private String orderObservation;
    @JsonProperty("totalValue")
    private Double totalValue;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("orderCreationDateTime")
    private String orderCreationDateTime;
    @JsonProperty("installationPartnerId")
    private String installationPartnerId;
    @JsonProperty("orderItems")
    private List<OrderItem> orderItems = null;
    @JsonProperty("customerIdOcc")
    private String customerIdOcc;
    @JsonProperty("payment")
    private List<Payment> payment = null;
    @JsonProperty("vendorId")
    private String vendorId;
    @JsonProperty("shippingGroups")
    private List<ShippingGroup> shippingGroups = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}