package br.com.frigelar.dto.request.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingGroupStatus{
    public String id;
    public String state;
    public String nf;
    public String nfKey;
    public String companyCnpj;
}
