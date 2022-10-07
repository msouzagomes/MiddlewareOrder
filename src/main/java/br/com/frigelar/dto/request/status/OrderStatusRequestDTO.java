package br.com.frigelar.dto.request.status;

import br.com.frigelar.dto.request.ShippingGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequestDTO {
    public String orderId;
    public String status;
    public List<ShippingGroupStatus> shippingGroups;
}
