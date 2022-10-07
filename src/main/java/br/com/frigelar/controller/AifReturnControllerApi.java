package br.com.frigelar.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frigelar.dto.request.PriceUpdateRequestDTO;
import br.com.frigelar.dto.request.StockUpdateRequestDTO;
import br.com.frigelar.dto.request.status.OrderStatusRequestDTO;
import br.com.frigelar.dto.request.status.UpdateOrderStatusDTO;
import br.com.frigelar.dto.response.MiddlewareResponse;
import br.com.frigelar.dto.response.PedidosResponseDTO;
import br.com.frigelar.service.AifReturnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Return Aif")
@RequestMapping("api/v1/aifReturn")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AifReturnControllerApi {

    private AifReturnService aifReturnService;

    public AifReturnControllerApi(AifReturnService aifReturnService) {
        this.aifReturnService = aifReturnService;
    }

    @ApiOperation(value = "Stock Update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = MiddlewareResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Access denied"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping(value = "stockUpdate", produces = "application/json")
    public ResponseEntity<MiddlewareResponse> stockUpdate(@RequestBody StockUpdateRequestDTO stockUpdateRequestDTO) {
        return new ResponseEntity(this.aifReturnService.stockUpdate(stockUpdateRequestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Price Update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = MiddlewareResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Access denied"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping(value = "priceUpdate", produces = "application/json")
    public ResponseEntity<MiddlewareResponse> priceUpdate(@RequestBody PriceUpdateRequestDTO priceUpdateRequestDTO) {
        return new ResponseEntity<MiddlewareResponse>(this.aifReturnService.priceUpdate(priceUpdateRequestDTO), HttpStatus.OK);
    }


    @ApiOperation(value = "Status Order Update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = MiddlewareResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Access denied"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping(value = "orderUpdate", produces = "application/json")
    public ResponseEntity<MiddlewareResponse> orderUpdate(@RequestBody OrderStatusRequestDTO orderStatusRequestDTO) {
        return new ResponseEntity<MiddlewareResponse>(this.aifReturnService.orderUpdate(orderStatusRequestDTO), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Find non integrated orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = PedidosResponseDTO.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Access denied"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @GetMapping(value = "/findNonIntegratedOrders", produces = "application/json")
    public ResponseEntity<PedidosResponseDTO> findNonIntegratedOrders() throws ParseException, IOException {
    	return new ResponseEntity<PedidosResponseDTO>(this.aifReturnService.findNonIntegratedOrders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Integration order Status Update")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = PedidosResponseDTO.class),
            @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 401, message = "Access denied"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @PutMapping(value = "/integrationStatusUpdate", produces = "application/json")
    public ResponseEntity<MiddlewareResponse> integrationStatusUpdate(@RequestBody UpdateOrderStatusDTO updateOrderStatusDTO) throws ParseException, IOException {
        return new ResponseEntity<MiddlewareResponse>(this.aifReturnService.integrationStatusUpdate(updateOrderStatusDTO), HttpStatus.OK);
    }
    
}
