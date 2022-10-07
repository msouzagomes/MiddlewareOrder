package br.com.frigelar.consumer.Impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.frigelar.consumer.IntegracaoSalesintegrationorder;
import br.com.frigelar.dto.request.status.UpdateOrderStatusDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Service
public class IntegracaoSalesintegrationorderImpl implements IntegracaoSalesintegrationorder {

    @Value("${spring.frigelar.salesintegrationorder.url}")
    private String urlIntegrationorder;

    @Value("${spring.frigelar.downOrder.url}")
    private String urlDownOrder;

    @Value("${spring.frigelar.lowOrder.url}")
    private String urlLowOrder;

    @Value("${spring.frigelar.paymentParameter.url}")
    private String urlPaymentParameter;

    @Value("${spring.frigelar.parametersClient.url}")
    private String urlparametersClient;

    @Value("${spring.frigelar.salesintegrationorder.authorization}")
    private String authorization;

    @Value("${spring.urlstatusSend.baseurl}")
    private String urlstatusSend;

    @Value("${spring.urlstatus.baseurl}")
    private String urlstatus;
    
    @Value("${spring.srvcOrder.baseurl}")
    private String urlSrvcOrde;

    @Override
    public Response salesIntegrationorder(String jsonBody) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
        		.connectTimeout(4, TimeUnit.MINUTES)
        		.readTimeout(4, TimeUnit.MINUTES)
        		.writeTimeout(4, TimeUnit.MINUTES)
        		.build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(urlIntegrationorder)
                .method("POST", body)
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "ARRAffinity=ce2ec86642108c70a09fb9ed3a2860f521d198a8d59649992bf37bbc88623bd1; ARRAffinitySameSite=ce2ec86642108c70a09fb9ed3a2860f521d198a8d59649992bf37bbc88623bd1")
                .build();
        return client.newCall(request).execute();
    }



    public Response getDownOrder() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(urlDownOrder)
                .method("GET", null)
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response getLowOrderResponsetDTO() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(urlLowOrder)
                .method("GET", null)
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response getPaymentParameter() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(urlPaymentParameter)
                .method("GET", null)
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response orderStatusUpdate(String jsonBody) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(urlstatus)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response statusSend(String jsonBody) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(urlstatusSend)
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response getParametersClientRequestDTO() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(urlparametersClient)
                .method("GET", null)
                .build();
        return client.newCall(request).execute();
    }
    
    public Response getDownOrderByCodigoFilial(String codigoFilial) throws IOException {
        String urlGetDownOrderByCodigoFilial = this.urlSrvcOrde.concat("/api/v1/srvcOrder/downOrder/".concat(codigoFilial).trim());

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(urlGetDownOrderByCodigoFilial)
                .method("GET", null)
                .build();
        return client.newCall(request).execute();
    }
    
    @Override
    public Response findNonIntegratedOrders() throws IOException {
        String urlFindNonIntegratedOrders = this.urlSrvcOrde.concat("/api/v1/srvcOrder/nonIntegratedOrders".trim());

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(urlFindNonIntegratedOrders)
                .method("GET", null)
                .build();
        
        return client.newCall(request).execute();
    }
    
    @Override
    public Response integrationStatusUpdate(UpdateOrderStatusDTO statusUpdateDTO)  throws IOException {

        ObjectMapper mapper = new ObjectMapper();        
        String jsonStatus = mapper.writeValueAsString(statusUpdateDTO);

        String urlStatusUpdate = this.urlSrvcOrde.concat("/api/v1/srvcOrder/integrationStatusUpdate".trim());
        
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonStatus);

        Request request = new Request.Builder()
        		.url(urlStatusUpdate).method("PUT", body)
        		.addHeader("Authorization", "true")
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }
}