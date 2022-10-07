package br.com.frigelar.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lastPurchaseDate",
        "x_tradingName",
        "GDPRProfileP13nConsentDate",
        "GDPRProfileP13nConsentGranted",
        "catalog",
        "parentOrganization",
        "shippingSurchargePriceList",
        "firstPurchaseDate",
        "profileType",
        "x_cpf",
        "loyaltyPrograms",
        "translations",
        "lastPurchaseAmount",
        "x_cellphoneNotification",
        "registrationDate",
        "links",
        "lifetimeAOV",
        "id",
        "x_dateOfBirth",
        "derivedSalePriceList",
        "daytimeTelephoneNumber",
        "customerContactId",
        "taxExempt",
        "active",
        "x_able_to_receive_order_discount",
        "lastVisitDate",
        "taxExemptionCode",
        "previousVisitDate",
        "x_document",
        "firstName",
        "creditCards",
        "lifetimeCurrencyCode",
        "derivedTaxExemptionCode",
        "x_isPJ",
        "userType",
        "secondaryOrganizations",
        "shippingAddresses",
        "derivedPriceListGroup",
        "lastName",
        "gender",
        "roles",
        "numberOfOrders",
        "locale",
        "login",
        "receiveEmailDate",
        "x_cellphone",
        "lifetimeSpend",
        "email",
        "x_telephone",
        "numberOfVisits",
        "x_companyName",
        "receiveEmail",
        "priceListGroup",
        "dateOfBirth",
        "x_gender",
        "x_IE",
        "repositoryId",
        "shippingAddress",
        "firstVisitDate",
        "lastActivity",
        "billingAddress",
        "derivedShippingSurchargePriceList"
})
public class ProfileResponseDTO {

    @JsonProperty("lastPurchaseDate")
    private String lastPurchaseDate;
    @JsonProperty("x_tradingName")
    private Object xTradingName;
    @JsonProperty("GDPRProfileP13nConsentDate")
    private Object gDPRProfileP13nConsentDate;
    @JsonProperty("GDPRProfileP13nConsentGranted")
    private Boolean gDPRProfileP13nConsentGranted;
    @JsonProperty("catalog")
    private Object catalog;
    @JsonProperty("parentOrganization")
    private Object parentOrganization;
    @JsonProperty("shippingSurchargePriceList")
    private Object shippingSurchargePriceList;
    @JsonProperty("firstPurchaseDate")
    private String firstPurchaseDate;
    @JsonProperty("profileType")
    private Object profileType;
    @JsonProperty("x_cpf")
    private Object xCpf;
    @JsonProperty("loyaltyPrograms")
    private List<Object> loyaltyPrograms = new ArrayList<Object>();
    @JsonProperty("translations")
    private Translations translations;
    @JsonProperty("lastPurchaseAmount")
    private Double lastPurchaseAmount;
    @JsonProperty("x_cellphoneNotification")
    private Boolean xCellphoneNotification;
    @JsonProperty("registrationDate")
    private String registrationDate;
    @JsonProperty("links")
    private List<Link> links = new ArrayList<Link>();
    @JsonProperty("lifetimeAOV")
    private Double lifetimeAOV;
    @JsonProperty("id")
    private String id;
    @JsonProperty("x_dateOfBirth")
    private String xDateOfBirth;
    @JsonProperty("derivedSalePriceList")
    private Object derivedSalePriceList;
    @JsonProperty("daytimeTelephoneNumber")
    private Object daytimeTelephoneNumber;
    @JsonProperty("customerContactId")
    private Object customerContactId;
    @JsonProperty("taxExempt")
    private Boolean taxExempt;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("x_able_to_receive_order_discount")
    private Boolean xAbleToReceiveOrderDiscount;
    @JsonProperty("lastVisitDate")
    private String lastVisitDate;
    @JsonProperty("taxExemptionCode")
    private Object taxExemptionCode;
    @JsonProperty("previousVisitDate")
    private String previousVisitDate;
    @JsonProperty("x_document")
    private String xDocument;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("creditCards")
    private List<Object> creditCards = new ArrayList<Object>();
    @JsonProperty("lifetimeCurrencyCode")
    private String lifetimeCurrencyCode;
    @JsonProperty("derivedTaxExemptionCode")
    private Object derivedTaxExemptionCode;
    @JsonProperty("x_isPJ")
    private Boolean xIsPJ;
    @JsonProperty("userType")
    private Object userType;
    @JsonProperty("secondaryOrganizations")
    private List<Object> secondaryOrganizations = new ArrayList<Object>();
    @JsonProperty("shippingAddresses")
    private List<ShippingAddress> shippingAddresses = new ArrayList<ShippingAddress>();
    @JsonProperty("derivedPriceListGroup")
    private Object derivedPriceListGroup;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("roles")
    private List<Object> roles = new ArrayList<Object>();
    @JsonProperty("numberOfOrders")
    private Integer numberOfOrders;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("login")
    private String login;
    @JsonProperty("receiveEmailDate")
    private Object receiveEmailDate;
    @JsonProperty("x_cellphone")
    private String xCellphone;
    @JsonProperty("lifetimeSpend")
    private Double lifetimeSpend;
    @JsonProperty("email")
    private String email;
    @JsonProperty("x_telephone")
    private Object xTelephone;
    @JsonProperty("numberOfVisits")
    private Integer numberOfVisits;
    @JsonProperty("x_companyName")
    private Object xCompanyName;
    @JsonProperty("receiveEmail")
    private String receiveEmail;
    @JsonProperty("priceListGroup")
    private Object priceListGroup;
    @JsonProperty("dateOfBirth")
    private Object dateOfBirth;
    @JsonProperty("x_gender")
    private Object xGender;
    @JsonProperty("x_IE")
    private Object xIE;
    @JsonProperty("repositoryId")
    private String repositoryId;
    @JsonProperty("shippingAddress")
    private ShippingAddress_ shippingAddress;
    @JsonProperty("firstVisitDate")
    private String firstVisitDate;
    @JsonProperty("lastActivity")
    private String lastActivity;
    @JsonProperty("billingAddress")
    private Object billingAddress;
    @JsonProperty("derivedShippingSurchargePriceList")
    private Object derivedShippingSurchargePriceList;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
