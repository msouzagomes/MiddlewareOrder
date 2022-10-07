package br.com.frigelar.service;

import java.io.IOException;
import java.util.List;

import br.com.frigelar.dto.request.PriceUpdateRequestDTO;
import br.com.frigelar.dto.request.StockUpdateRequestDTO;
import br.com.frigelar.dto.request.status.OrderStatusRequestDTO;
import br.com.frigelar.dto.request.status.UpdateOrderStatusDTO;
import br.com.frigelar.dto.response.MiddlewareResponse;
import br.com.frigelar.dto.response.PedidosResponseDTO;

public interface AifReturnService {
    List<MiddlewareResponse> stockUpdate(StockUpdateRequestDTO stockUpdateRequestDTO);
    MiddlewareResponse priceUpdate(PriceUpdateRequestDTO priceUpdateRequestDTO);
    MiddlewareResponse orderUpdate(OrderStatusRequestDTO orderStatusRequestDTO);
    PedidosResponseDTO findNonIntegratedOrders() throws IOException;
    MiddlewareResponse integrationStatusUpdate(UpdateOrderStatusDTO updateOrderStatusDTO) throws IOException;
}