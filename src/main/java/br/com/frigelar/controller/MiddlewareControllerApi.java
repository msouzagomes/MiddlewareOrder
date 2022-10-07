package br.com.frigelar.controller;

import br.com.frigelar.dto.request.OccRequestDTO;
import br.com.frigelar.dto.response.MiddlewareResponse;
import br.com.frigelar.service.MiddlewareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Api(tags = "Middleware Order")
@RequestMapping("api/v1/middlewareOrder")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MiddlewareControllerApi {

    private MiddlewareService middlewareService;

    public MiddlewareControllerApi(MiddlewareService middlewareService) {
        this.middlewareService = middlewareService;
    }

    @ApiOperation(value = "Send Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = MiddlewareResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Access denied"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping(value = "/sendOrder", produces = "application/json")
    public ResponseEntity<MiddlewareResponse> sendOrder(@RequestBody OccRequestDTO occRequestDTO) throws Exception {
        return new ResponseEntity(this.middlewareService.sendOrder(occRequestDTO), HttpStatus.CREATED);
    }

}
