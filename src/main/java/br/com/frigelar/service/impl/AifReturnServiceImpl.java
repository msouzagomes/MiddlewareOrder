package br.com.frigelar.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.frigelar.consumer.IntegracaoOcc;
import br.com.frigelar.consumer.IntegracaoSalesintegrationorder;
import br.com.frigelar.consumer.Impl.model.Item;
import br.com.frigelar.consumer.Impl.model.PriceUpdateDTO;
import br.com.frigelar.dto.request.CreateStockRequestDTO;
import br.com.frigelar.dto.request.PriceUpdateRequestDTO;
import br.com.frigelar.dto.request.Stock;
import br.com.frigelar.dto.request.StockUpdateRequestDTO;
import br.com.frigelar.dto.request.status.OrderStatusRequestDTO;
import br.com.frigelar.dto.request.status.UpdateOrderStatusDTO;
import br.com.frigelar.dto.response.MiddlewareResponse;
import br.com.frigelar.dto.response.PedidosDTO;
import br.com.frigelar.dto.response.PedidosResponseDTO;
import br.com.frigelar.dto.response.SalesintegrationorderRequestDTO;
import br.com.frigelar.service.AifReturnService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

@Slf4j
@Service
public class AifReturnServiceImpl implements AifReturnService {

    @Value("${spring.occ.baseurl}")
    private String url;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    private IntegracaoOcc integracaoOcc;

    private List<String> locationId;

    private Boolean msgStock;

    private IntegracaoSalesintegrationorder integracaoSalesintegrationorder;

    public AifReturnServiceImpl(IntegracaoOcc integracaoOcc, IntegracaoSalesintegrationorder integracaoSalesintegrationorder) {
        this.integracaoOcc = integracaoOcc;
        this.integracaoSalesintegrationorder = integracaoSalesintegrationorder;
    }

    @Override
    public List<MiddlewareResponse> stockUpdate(StockUpdateRequestDTO stockUpdateRequestDTO) {
        List<MiddlewareResponse> response = new ArrayList<>();
        log.info("Inicir stockUpdate");

        try {
            String urlStockUpdate = url.concat("/api/v1/integrationOcc/createStock").trim();
            log.info("url urlStockUpdate : " + urlStockUpdate);
            stockUpdateRequestDTO.getStock().stream().forEach(s->{
                response.add(this.createStock(s, urlStockUpdate));
            });
        } catch (Exception ex) {
            log.error("Exception " + ex.getMessage());
            response.add(MiddlewareResponse.builder()
                    .code("1")
                    .message("Exception " + ex.getMessage())
                    .build());
        }
        return response;
    }

    @Override
    public MiddlewareResponse priceUpdate(PriceUpdateRequestDTO priceUpdateRequestDTO)  {
        log.info("Inicair priceUpdate");
        try {
            String integracaoOccUrl = url.concat("/api/v1/integrationOcc/atualizarPreco").trim();
            log.info("url integracaoOccUrl : " + integracaoOccUrl);
            String jsonSaveCategories = mapper.writeValueAsString(this.getPriceUpdateDTO(priceUpdateRequestDTO));
            log.info("json jsonSaveCategories : " + jsonSaveCategories);
            Response rs = this.integracaoOcc.puttIntegracaoOcc(jsonSaveCategories, integracaoOccUrl);
            if(200 == rs.code()){
            	closeResponse(rs);
                return MiddlewareResponse.builder()
                        .code("0")
                        .message("Sucesso")
                        .build();
            }
            JSONObject json = new JSONObject();
            json.put("result",rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            closeResponse(rs);
            return mapper.readValue(json.getString("result"), MiddlewareResponse.class);
        } catch (Exception ex) {
            log.error("Exception " + ex.getMessage());
            return MiddlewareResponse.builder()
                    .code("1")
                    .message("Exception " + ex.getMessage())
                    .build();
        }
    }

    private PriceUpdateDTO getPriceUpdateDTO(PriceUpdateRequestDTO priceUpdateRequestDTO){
        List<Item> items = new ArrayList<>();
        items.add(Item.builder()
                .productId(priceUpdateRequestDTO.getKitId())
                .skuId(priceUpdateRequestDTO.getSku())
                .priceListId("frigelarDefaultPJ_listPrices")
                .listPrice(priceUpdateRequestDTO.getFrigelarPj().getListPrices().doubleValue() != 0.0 ? priceUpdateRequestDTO.getFrigelarPj().getListPrices().doubleValue() : null)
                .build());
        items.add(Item.builder()
                .productId(priceUpdateRequestDTO.getKitId())
                .skuId(priceUpdateRequestDTO.getSku())
                .priceListId("frigelarDefaultPJ_salePrices")
                .listPrice(priceUpdateRequestDTO.getFrigelarPj().getSalePrices().doubleValue() != 0.0 ? priceUpdateRequestDTO.getFrigelarPj().getSalePrices().doubleValue() : null)
                .build());
        items.add(Item.builder()
                .productId(priceUpdateRequestDTO.getKitId())
                .skuId(priceUpdateRequestDTO.getSku())
                .priceListId("frigelarDefaultPF_listPrices")
                .listPrice(priceUpdateRequestDTO.getFrigelarPf().getListPrices().doubleValue() != 0.0 ? priceUpdateRequestDTO.getFrigelarPf().getListPrices().doubleValue() : null)
                .build());
        items.add(Item.builder()
                .productId(priceUpdateRequestDTO.getKitId())
                .skuId(priceUpdateRequestDTO.getSku())
                .priceListId("frigelarDefaultPF_salePrices")
                .listPrice(priceUpdateRequestDTO.getFrigelarPf().getSalePrices().doubleValue() != 0.0 ? priceUpdateRequestDTO.getFrigelarPf().getSalePrices().doubleValue()  : null)
                .build());
        return PriceUpdateDTO.builder()
                .items(items)
                .build();
    }

    private MiddlewareResponse createStock(Stock stock, String url){
        try{
            String jsonCreateStock = mapper.writeValueAsString(CreateStockRequestDTO.builder()
                    .id(stock.getId())
                    .locationId(stock.getLocationId())
                    .stockLevel(stock.getStockLevel().toString())
                    .stockLevelTotal(stock.getStockLevelTotal().getStockLevel().toString())
                    .build());
            log.info("json jsonSaveCategories : " + jsonCreateStock);
            Response rs = this.integracaoOcc.postIntegracaoOcc(jsonCreateStock, url);
            if(200 == rs.code()){
            	closeResponse(rs);
                return MiddlewareResponse.builder()
                        .code("0")
                        .message("Sucesso")
                        .build();
            }
            JSONObject json = new JSONObject();
            json.put("result",rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            closeResponse(rs);
            return mapper.readValue(json.getString("result"), MiddlewareResponse.class);
        }catch (Exception e){
            return MiddlewareResponse.builder()
                    .code("1")
                    .message(e.getMessage())
                    .status("500")
                    .build();
        }
    }

    @Override
    public MiddlewareResponse orderUpdate(OrderStatusRequestDTO orderStatusRequestDTO) {
        try{
            String jsonStatus = mapper.writeValueAsString(orderStatusRequestDTO);
            log.info("json jsonStatus : " + jsonStatus);
            Response rs = this.integracaoSalesintegrationorder.orderStatusUpdate(jsonStatus);
            if(200 == rs.code()){
            	closeResponse(rs);
                this.integracaoSalesintegrationorder.statusSend(jsonStatus);
                return MiddlewareResponse.builder()
                        .code("0")
                        .message("Sucesso")
                        .build();
            }
            JSONObject json = new JSONObject();
            json.put("result",rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            closeResponse(rs);
            return mapper.readValue(json.getString("result"), MiddlewareResponse.class);
        }catch (Exception e){
            return MiddlewareResponse.builder()
                    .code("1")
                    .message(e.getMessage())
                    .status("500")
                    .build();
        }
    }
    
	@Override
	public PedidosResponseDTO findNonIntegratedOrders() throws IOException {
		Response response = this.integracaoSalesintegrationorder.findNonIntegratedOrders();

		PedidosResponseDTO pedidosResponseDTO = new PedidosResponseDTO();

		if (Objects.nonNull(response) && 200 == response.code()) {

			Gson gson = new Gson();
			PedidosDTO pedidosDto = gson.fromJson(response.body().string(), PedidosDTO.class);

			if (Objects.nonNull(pedidosDto)) {
				List<SalesintegrationorderRequestDTO> listaPedidos = new ArrayList<SalesintegrationorderRequestDTO>();
				pedidosDto.getPedidos().forEach(pedido -> {
					SalesintegrationorderRequestDTO salesintegrationorderRequestDTO = gson.fromJson(pedido,
							SalesintegrationorderRequestDTO.class);
					listaPedidos.add(salesintegrationorderRequestDTO);
				});
				pedidosResponseDTO.setSalesIntegrationOrder(listaPedidos);
				closeResponse(response);
				return pedidosResponseDTO;
			}
		}
		closeResponse(response);
		return pedidosResponseDTO;
	}
    
    @Override
    public MiddlewareResponse integrationStatusUpdate(UpdateOrderStatusDTO updateOrderStatusDTO) throws IOException {
    	Response response = null;
        try {
            response = this.integracaoSalesintegrationorder.integrationStatusUpdate(updateOrderStatusDTO);

            if (Objects.nonNull(response) && 200 == response.code()) {
            	closeResponse(response);
                return MiddlewareResponse.builder().code("200").message("OK").build();
            }
            closeResponse(response);
            return MiddlewareResponse.builder().code("400").message("Bad Request").build();

        } catch (Exception e) {
        	closeResponse(response);
            return MiddlewareResponse.builder().code("500").message("Interna Server Error").build();
        }    
    }

	private void closeResponse(Response response) {
		if(Objects.nonNull(response)) {
			response.close();
		}
	}
}
