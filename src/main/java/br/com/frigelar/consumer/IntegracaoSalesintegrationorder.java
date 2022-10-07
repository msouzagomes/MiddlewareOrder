package br.com.frigelar.consumer;

import java.io.IOException;

import br.com.frigelar.dto.request.status.UpdateOrderStatusDTO;
import okhttp3.Response;

public interface IntegracaoSalesintegrationorder {
    Response salesIntegrationorder(String jsonBody) throws IOException;
    Response getDownOrder() throws IOException;
    Response getLowOrderResponsetDTO() throws IOException;
    Response getPaymentParameter() throws IOException;
    Response orderStatusUpdate(String jsonBody) throws IOException;
    Response statusSend(String jsonBody) throws IOException;
    Response getParametersClientRequestDTO() throws IOException;
    Response getDownOrderByCodigoFilial(String codigoFilial) throws IOException;
    Response findNonIntegratedOrders() throws IOException;
    Response integrationStatusUpdate(UpdateOrderStatusDTO statusUpdateDTO)  throws IOException;    
}
