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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x_additionalShippingGroupInfo",
        "country",
        "lastName",
        "types",
        "address3",
        "city",
        "address2",
        "address1",
        "postalCode",
        "x_originPostalCode",
        "county",
        "x_isServiceShipping",
        "x_isExpressShipping",
        "firstName",
        "externalAddressId",
        "phoneNumber",
        "repositoryId",
        "state",
        "x_referencePoint",
        "x_scheduleShipping"
})
public class ShippingAddress_ {

    @JsonProperty("x_additionalShippingGroupInfo")
    private Object xAdditionalShippingGroupInfo;
    @JsonProperty("country")
    private String country;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("types")
    private List<Object> types = new ArrayList<Object>();
    @JsonProperty("address3")
    private Object address3;
    @JsonProperty("city")
    private String city;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("x_originPostalCode")
    private Object xOriginPostalCode;
    @JsonProperty("county")
    private String county;
    @JsonProperty("x_isServiceShipping")
    private Boolean xIsServiceShipping;
    @JsonProperty("x_isExpressShipping")
    private Boolean xIsExpressShipping;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("externalAddressId")
    private Object externalAddressId;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("repositoryId")
    private String repositoryId;
    @JsonProperty("state")
    private String state;
    @JsonProperty("x_referencePoint")
    private Object xReferencePoint;
    @JsonProperty("x_scheduleShipping")
    private Object xScheduleShipping;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
