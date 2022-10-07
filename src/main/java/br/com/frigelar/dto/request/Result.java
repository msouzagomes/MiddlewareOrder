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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "brinde",
        "carrierId",
        "custFinalUser",
        "discountValue",
        "dlvTermId",
        "fineCode",
        "fiscalDocumentType",
        "id",
        "installationPartnerId",
        "interestCode",
        "inventLocationId",
        "itemsValue",
        "languageId",
        "marketPlaceOrderId",
        "message",
        "openTextField",
        "orcamento",
        "orderCode",
        "orderCreationDateTime",
        "orderStatus",
        "presenceType",
        "reservation",
        "salesIntegrationOrderAddrContract",
        "salesIntegrationOrderCustContract",
        "salesIntegrationOrderItemsContract",
        "salesIntegrationOrderPaymContract",
        "salesOperation",
        "salesOrderStatus",
        "salesResponsible",
        "salesType",
        "shippingEstimated",
        "shippingValue",
        "shippmentGroupId",
        "success",
        "totalValue",
        "utmCampaign",
        "utmSource",
        "vendorId",
        "warehouseId",
        "wmsLocationId",
        "salesIntegrationOrderLogistContract"
})
public class Result {

    @JsonProperty("brinde")
    private Boolean brinde;
    @JsonProperty("carrierId")
    private String carrierId;
    @JsonProperty("custFinalUser")
    private Boolean custFinalUser;
    @JsonProperty("discountValue")
    private Integer discountValue;
    @JsonProperty("dlvTermId")
    private String dlvTermId;
    @JsonProperty("fineCode")
    private String fineCode;
    @JsonProperty("fiscalDocumentType")
    private String fiscalDocumentType;
    @JsonProperty("id")
    private String id;
    @JsonProperty("installationPartnerId")
    private String installationPartnerId;
    @JsonProperty("interestCode")
    private String interestCode;
    @JsonProperty("inventLocationId")
    private String inventLocationId;
    @JsonProperty("itemsValue")
    private Integer itemsValue;
    @JsonProperty("languageId")
    private String languageId;
    @JsonProperty("marketPlaceOrderId")
    private String marketPlaceOrderId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("openTextField")
    private String openTextField;
    @JsonProperty("orcamento")
    private Boolean orcamento;
    @JsonProperty("orderCode")
    private String orderCode;
    @JsonProperty("orderCreationDateTime")
    private String orderCreationDateTime;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("presenceType")
    private String presenceType;
    @JsonProperty("reservation")
    private String reservation;
    @JsonProperty("salesIntegrationOrderAddrContract")
    private List<SalesIntegrationOrderAddrContract> salesIntegrationOrderAddrContract = new ArrayList<SalesIntegrationOrderAddrContract>();
    @JsonProperty("salesIntegrationOrderCustContract")
    private List<SalesIntegrationOrderCustContract> salesIntegrationOrderCustContract = new ArrayList<SalesIntegrationOrderCustContract>();
    @JsonProperty("salesIntegrationOrderItemsContract")
    private List<SalesIntegrationOrderItemsContract> salesIntegrationOrderItemsContract = new ArrayList<SalesIntegrationOrderItemsContract>();
    @JsonProperty("salesIntegrationOrderPaymContract")
    private List<SalesIntegrationOrderPaymContract> salesIntegrationOrderPaymContract = new ArrayList<SalesIntegrationOrderPaymContract>();
    @JsonProperty("salesOperation")
    private String salesOperation;
    @JsonProperty("salesOrderStatus")
    private String salesOrderStatus;
    @JsonProperty("salesResponsible")
    private String salesResponsible;
    @JsonProperty("salesType")
    private String salesType;
    @JsonProperty("shippingEstimated")
    private String shippingEstimated;
    @JsonProperty("shippingValue")
    private Integer shippingValue;
    @JsonProperty("shippmentGroupId")
    private String shippmentGroupId;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("totalValue")
    private Integer totalValue;
    @JsonProperty("utmCampaign")
    private String utmCampaign;
    @JsonProperty("utmSource")
    private String utmSource;
    @JsonProperty("vendorId")
    private String vendorId;
    @JsonProperty("warehouseId")
    private String warehouseId;
    @JsonProperty("wmsLocationId")
    private String wmsLocationId;
    @JsonProperty("salesIntegrationOrderLogistContract")
    private List<SalesIntegrationOrderLogistContract> salesIntegrationOrderLogistContract = new ArrayList<SalesIntegrationOrderLogistContract>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
