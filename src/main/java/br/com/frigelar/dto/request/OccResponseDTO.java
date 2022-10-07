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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "result",
        "errors"
})
public class OccResponseDTO {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("errors")
    private Object errors;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
