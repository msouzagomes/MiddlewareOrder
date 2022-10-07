package br.com.frigelar.service.impl;

import br.com.frigelar.consumer.IntegracaoOcc;
import br.com.frigelar.consumer.IntegracaoSalesintegrationorder;
import br.com.frigelar.dto.request.*;
import br.com.frigelar.dto.response.SalesIntegrationOrderAddrContract;
import br.com.frigelar.dto.response.SalesIntegrationOrderCustContract;
import br.com.frigelar.dto.response.SalesIntegrationOrderItemsContract;
import br.com.frigelar.dto.response.SalesIntegrationOrderLogistContract;
import br.com.frigelar.dto.response.SalesIntegrationOrderPaymContract;
import br.com.frigelar.dto.response.*;
import br.com.frigelar.service.MiddlewareService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class MiddlewareServiceImpl implements MiddlewareService {
    private static final Integer STATUS_CODE_REPROCESS = 0;
    private static final Integer STATUS_CODE_SUCCESS = 1;
    private static final Integer STATUS_CODE_FAIL = 2;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Value("${spring.occ.baseurl}")
    private String url;
    @Value("${spring.srvcOrder.baseurl}")
    private String urlSrvcOrde;
    @Value("${spring.branchClient.baseurl}")
    private String urlBranchClient;

    private IntegracaoSalesintegrationorder integracaoSalesIntegrationOrder;
    private IntegracaoOcc integracaoOcc;

    public MiddlewareServiceImpl(IntegracaoSalesintegrationorder integracaoSalesIntegrationOrder, IntegracaoOcc integracaoOcc) {
        this.integracaoSalesIntegrationOrder = integracaoSalesIntegrationOrder;
        this.integracaoOcc = integracaoOcc;
    }

    @Override
    public MiddlewareResponse sendOrder(OccRequestDTO occRequestDTO) throws Exception {
        try {
            log.info("Iniciando método MiddlewareServiceImpl.sendOrder");
            ProfileResponseDTO profile = this.getProfileResponse(occRequestDTO.getCustomerIdOcc());
            if (profile != null) {
                occRequestDTO.getShippingGroups().stream().forEach(shippingGroup -> {

                    try {
                        Map<String, String> map = getJsonAndSendSalesIntegrationOrder(occRequestDTO, shippingGroup, profile);
                        String jsonSalesIntegrationOrder = map.get("jsonSalesIntegrationOrder");
                        String result = map.get("result");

                        OccResponseDTO occResponse = mapper.readValue(result, OccResponseDTO.class);
                        Integer status = occResponse.getResult().getSuccess() ? STATUS_CODE_SUCCESS : STATUS_CODE_FAIL;
                        String messageResult = occResponse.getResult() != null && occResponse.getResult().getMessage() != null ?
                                occResponse.getResult().getMessage() : "OccResponseDTO retornando null";

                        sendOrder(occRequestDTO, jsonSalesIntegrationOrder, status, messageResult, shippingGroup.getShippingGroupId());
                    } catch (Exception e) {
                        try {
                            sendOrder(occRequestDTO, null, STATUS_CODE_FAIL, e.getMessage(), getShippingGroupIdWhenHasError(occRequestDTO));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            } else {
                String messageError = "Não foi encontrado nenhum registro no Integration OCC com o orderId " + occRequestDTO.getOrderId();
                sendOrder(occRequestDTO, null, STATUS_CODE_FAIL, messageError, getShippingGroupIdWhenHasError(occRequestDTO));
                return MiddlewareResponse.builder()
                        .code("1")
                        .message(messageError)
                        .build();
            }
        } catch (Exception e) {
            sendOrder(occRequestDTO, null, STATUS_CODE_FAIL, e.getMessage(), getShippingGroupIdWhenHasError(occRequestDTO));
            return MiddlewareResponse.builder()
                    .code(STATUS_CODE_FAIL.toString())
                    .message(e.getMessage())
                    .build();
        }

        return MiddlewareResponse.builder()
                .code(STATUS_CODE_SUCCESS.toString())
                .message("Sucesso")
                .build();
    }

    private SalesintegrationorderRequestDTO getSalesIntegrationOrderRequestDTO(PaymentParameterResponsetDTO paymentParameter, LowOrderResponsetDTO lowOrder, DownOrderResponsetDTO downOr, OccRequestDTO dto, ProfileResponseDTO profile, ShippingGroup shippingGroup, ParametersClientRequestDTO parametersClientRequestDTO) throws IOException, JSONException {
        String branchCode = getBranchCodeByLocation(shippingGroup.getWarehouseId());
        SalesintegrationorderRequestDTO salesintegrationorderRequestDTO = SalesintegrationorderRequestDTO.builder()
                .brinde(Boolean.FALSE) //
                .carrierId("") //
                .custFinalUser(lowOrder.getProductUser().equalsIgnoreCase("SIM") ? Boolean.TRUE : Boolean.FALSE)
                .dlvTermId("") //
                .fineCode(lowOrder != null ? lowOrder.getFineCode() : "")
                .id(dto.getOrderId())
                .installationPartnerId(dto.getInstallationPartnerId())
                .interestCode(lowOrder != null ? lowOrder.getInterestCode() : "")
                .inventLocationId("")
                .itemsValue(Objects.nonNull(dto.getItemsValue()) ? dto.getItemsValue() : NumberUtils.DOUBLE_ZERO)
                .languageId(lowOrder != null ? lowOrder.getLanguageUsedErp() : "")
                .marketPlaceOrderId("")
                .message("")
                .openTextField(dto.getOrderObservation())
                .orcamento(Boolean.FALSE)
                .discountValue(dto.getDiscountValue())
                .orderCode(dto.getOrderId())
                .orderCreationDateTime(dto.getOrderCreationDateTime())
                .orderStatus(dto.getOrderStatus())
                .presenceType(lowOrder != null ? lowOrder.getPresenceType() : "")
                .reservation(lowOrder != null ? lowOrder.getReserveItemsAutomatically() : "")
                .salesIntegrationOrderCustContract(this.getOrderCustContract(profile, parametersClientRequestDTO))
                .salesIntegrationOrderItemsContract(this.getSalesIntegrationOrderItemsContract(shippingGroup.getShippingGroupsItems()))
                .salesIntegrationOrderPaymContract(this.geOrderPaymContract(paymentParameter, dto.getPayment()))
                .salesIntegrationOrderLogistContract(this.getOrderLogistContract(shippingGroup))
                .salesOrderStatus("")
                .salesResponsible(lowOrder != null ? lowOrder.getCodeSeller() : "")
                .salesType(lowOrder != null ? lowOrder.getTypeOrderSale() : "")
                .shippingEstimated(shippingGroup != null ? shippingGroup.getShippingEstimate() : "")
                .shippingValue(Objects.nonNull(dto.getShippingValue()) ? dto.getShippingValue() : NumberUtils.DOUBLE_ZERO)
                .shippmentGroupId(shippingGroup.getShippingGroupId())
                .success(Boolean.FALSE)
                .totalValue(Objects.nonNull(dto.getTotalValue()) ? dto.getTotalValue() : NumberUtils.DOUBLE_ZERO)
                .utmCampaign("")
                .utmSource("")
                .vendorId(dto.getVendorId())
                .warehouseId(branchCode)
                .wmsLocationId("")
                .build();

        setFiscalDocumentTypeAndSalesOperation(salesintegrationorderRequestDTO, branchCode, profile);

        return salesintegrationorderRequestDTO;
    }

    private void setFiscalDocumentTypeAndSalesOperation(SalesintegrationorderRequestDTO salesintegrationorderRequestDTO, String warehouseId, ProfileResponseDTO profile) throws IOException, JSONException {
        salesintegrationorderRequestDTO.setFiscalDocumentType("");
        salesintegrationorderRequestDTO.setSalesOperation("");

        DownOrderResponsetDTO downOrderResponsetDTO = this.getDownOrderByCodigoFilial(warehouseId);

        if (Objects.nonNull(downOrderResponsetDTO)) {
            if (!StringUtils.isEmpty(downOrderResponsetDTO.getTaxDocumentType())) {
                salesintegrationorderRequestDTO.setFiscalDocumentType(downOrderResponsetDTO.getTaxDocumentType());
            }

            if (profile.getXIsPJ() && !StringUtils.isEmpty(downOrderResponsetDTO.getOperatorTypePj())) {
                salesintegrationorderRequestDTO.setSalesOperation(downOrderResponsetDTO.getOperatorTypePj());
            }

            if (!profile.getXIsPJ() && !StringUtils.isEmpty(downOrderResponsetDTO.getOperatorTypeDf())) {
                salesintegrationorderRequestDTO.setSalesOperation(downOrderResponsetDTO.getOperatorTypeDf());
            }
        }
    }

    private String getBranchCodeByLocation(String itemWarehouseId) {
           try {
               String urlBranch = String.format("%s/%s/%s/%s", urlBranchClient, "branches", itemWarehouseId, "findByLocation");
               log.info("urlBranch" + urlBranch);
               Response rs = this.integracaoOcc.getIntegracaoOcc(urlBranch);

               if (rs.code() == 200) {
                   JSONObject json = new JSONObject();
                   json.put("result", rs.body().string());
                   String resultString = json.getString("result");

                   if (resultString != null && !resultString.trim().isEmpty()) {
                       ObjectMapper mapper = new ObjectMapper();
                       rs.close();
                       BranchResponseDTO branchResponseDTO = mapper.readValue(resultString, BranchResponseDTO.class);
                       return branchResponseDTO.getCode();
                   }
               }
               rs.close();
           } catch (Exception exception) {
               log.error("Erro ao chamar getBranchCodeByLocation -> " + exception.getSuppressed());
           }

            return itemWarehouseId;
    }

    private List<SalesIntegrationOrderItemsContract> getSalesIntegrationOrderItemsContract(List<ShippingGroupsItem> orderItems) {
        List<SalesIntegrationOrderItemsContract> cont = new ArrayList<>();
        orderItems.stream().forEach(o -> {
            cont.add(SalesIntegrationOrderItemsContract.builder()
                    .discountValue(o.getItemDiscountValue())
                    .name(o.getItemName())
                    .itemType(o.getItemType())
                    .finalValue(Objects.nonNull(o.getItemFinalValue()) ? o.getItemFinalValue() : NumberUtils.DOUBLE_ZERO)
                    .value(Objects.nonNull(o.getItemValue()) ? o.getItemValue() : NumberUtils.DOUBLE_ZERO)
                    .productId(o.getProductId())
                    .qty(o.getQuantity())
                    .skuId(0)//TODO: Esse campo deve ser revisto com o pessoal do AX já que o skuId é String e o AX está preparado para receber Integer
                    .weight(Objects.nonNull(o.getWeight()) ? o.getWeight() : NumberUtils.DOUBLE_ZERO)
                    .build());
        });
        return cont;
    }

    private List<SalesIntegrationOrderCustContract> getOrderCustContract(ProfileResponseDTO profile, ParametersClientRequestDTO parametersClientRequestDTO) {
        List<SalesIntegrationOrderCustContract> salesIntegrationOrderCustContracts = new ArrayList<>();
        salesIntegrationOrderCustContracts.add(SalesIntegrationOrderCustContract.builder()
                .cellPhone(profile.getXCellphone())
                .cnpjCpfNum(profile.getXDocument())
                .currency(parametersClientRequestDTO.getCurrencyCode())
                .custGroup(parametersClientRequestDTO.getCustomerGroup())
                .email(profile.getEmail())
                .fineCode(parametersClientRequestDTO.getFineCode())
                .firstName(profile.getFirstName())
                .id(profile.getId())
                .ieNum("")
                .interestCode(parametersClientRequestDTO.getInterestCode())
                .isCorporation(Boolean.FALSE)
                //.isPrimary(parametersClientRequestDTO.getMainAddress())
                .isPrimary(Boolean.FALSE)
                .lastName(profile.getLastName())
                .phone("")
                .tradeName("")
                .build());
        return salesIntegrationOrderCustContracts;
    }

    private List<SalesIntegrationOrderPaymContract> geOrderPaymContract(PaymentParameterResponsetDTO paymentParameter, List<Payment> payments) {
        List<SalesIntegrationOrderPaymContract> orderPaym = new ArrayList<>();
        payments.stream().forEach(payment -> {
            orderPaym.add(SalesIntegrationOrderPaymContract.builder()
                    .cardFirstDigits(payment.getCardLastDigits())
                    .installmentNumber(Objects.nonNull(payment.getInstallmentNumber()) ? Integer.parseInt(payment.getInstallmentNumber()) : 0)
                    .installmentValue(Objects.nonNull(payment.getInstallmentValue()) ? payment.getInstallmentValue() : NumberUtils.DOUBLE_ZERO)
                    .installments(Integer.parseInt(payment.getInstallments()))
                    .paymMode(paymentParameter.getPaymentMethod())
                    .paymSched(paymentParameter.getPaymentPlan())
                    .payment(paymentParameter.getFormPayment())
                    .paymentGateway(payment.getPaymentGateway())
                    .paymentNSU(payment.getNsu())
                    .paymentTID(payment.getTId())
                    .paymentTotalValue(Objects.nonNull(payment.getPaymentTotalValue()) ? payment.getPaymentTotalValue() : NumberUtils.DOUBLE_ZERO)
                    .postProfile(paymentParameter.getInvoiceLaunchProfile())
                    .paymentType(payment.getPaymentType())
                    .system(payment.getPaymentSystem())
                    .transactionId("")
                    .build());
        });
        return orderPaym;
    }

    private List<SalesIntegrationOrderAddrContract> getOrderAddrContract(ShippingGroup shippingGroups) {
        List<SalesIntegrationOrderAddrContract> orderAddrContract = new ArrayList<>();
        orderAddrContract.add(SalesIntegrationOrderAddrContract.builder()
                .city(shippingGroups.getShippingAddressCity())
                .complement(shippingGroups.getShippingAddressComplement())
                .country(shippingGroups.getShippingAddressCountry())
                .isPrimary(Boolean.TRUE)
                .neighborhood(shippingGroups.getShippingAddressNeighborhood())
                .number(shippingGroups.getShippingAddressNumber())
                .postalCode(shippingGroups.getShippingAddressPostalCode())
                .receiverName(shippingGroups.getShippingAddressReceiverName())
                .state(shippingGroups.getShippingAddressState())
                .street(shippingGroups.getShippingAddressStreet())
                .build());
        return orderAddrContract;
    }

    private List<SalesIntegrationOrderLogistContract> getOrderLogistContract(ShippingGroup shippingGroup) {
        List<SalesIntegrationOrderLogistContract> orderLogistContract = new ArrayList<SalesIntegrationOrderLogistContract>();
        if (Objects.nonNull(shippingGroup)) {
            orderLogistContract.add(SalesIntegrationOrderLogistContract.builder()
                    .carrierCNPJ(!StringUtils.isEmpty(shippingGroup.getCourierCnpj()) ? shippingGroup.getCourierCnpj() : "")
                    .carrierName(!StringUtils.isEmpty(shippingGroup.getCourierName()) ? shippingGroup.getCourierName() : "")
                    .price(Objects.nonNull(shippingGroup.getShippingValue()) ? shippingGroup.getShippingValue() : NumberUtils.DOUBLE_ZERO)
                    .shippingAddressReceiverCellPhone(!StringUtils.isEmpty(shippingGroup.getShippingAddressReceiverCellPhone()) ? shippingGroup.getShippingAddressReceiverCellPhone() : "")
                    .shippingAddressReceiverName(!StringUtils.isEmpty(shippingGroup.getShippingAddressReceiverName()) ? shippingGroup.getShippingAddressReceiverName() : "")
                    .shippingEstimate(!StringUtils.isEmpty(shippingGroup.getShippingEstimate()) ? shippingGroup.getShippingEstimate() : "")
                    .shippingEstimatedDate(!StringUtils.isEmpty(shippingGroup.getShippingEstimateDate()) ? shippingGroup.getShippingEstimateDate() : "")
                    .warehouseId(!StringUtils.isEmpty(shippingGroup.getWarehouseId()) ? getBranchCodeByLocation(shippingGroup.getWarehouseId()) : "")
                    .selectedSLA(!StringUtils.isEmpty(shippingGroup.getDeliveryType()) ? shippingGroup.getDeliveryType() : "")
                    .build());
        }
        return orderLogistContract;
    }

    private ProfileResponseDTO getProfileResponse(String id) throws JSONException, IOException {
        String urlProfile = this.url.concat("/api/v1/integrationOcc/profile/").concat(id).trim();
        log.info("urlProfile" + urlProfile);
        Response rs = this.integracaoOcc.getIntegracaoOcc(urlProfile);

        if (rs.code() == 200) {
            JSONObject json = new JSONObject();
            json.put("result", rs.body().string());
            String resultString = json.getString("result");

            if (resultString != null && !resultString.trim().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                rs.close();
                return mapper.readValue(resultString, ProfileResponseDTO.class);
            }
        }

        rs.close();
        return null;
    }

    private void sendOrder(OccRequestDTO dto, String jsonSalesintegrationorder, Integer status, String respAif, String shippingGroup) throws Exception {
        String json = mapper.writeValueAsString(SendOrderRequestDTO.builder()
                .jsonAif(jsonSalesintegrationorder)
                .jsonOcc(mapper.writeValueAsString(dto))
                .statusEnv(status.toString())
                .respAif(respAif)
                .shippingGroupId(shippingGroup)
                .build());

        String urlOrder = urlSrvcOrde.concat("/api/v1/srvcOrder/sendOrder").trim();
        log.info("urlOrder" + urlOrder);

        Response rs = integracaoOcc.postIntegracaoOcc(json, urlOrder);
        if (rs.code() == 201) {
            rs.close();
            log.info("Enviado com sucesso");
        } else {
            rs.close();
            throw new Exception("Erro ao Gravar Json");
        }
    }

    private DownOrderResponsetDTO getDownOrderByCodigoFilial(String codigoFilial) throws IOException, JSONException {
        String branchCode = getBranchCodeByLocation(codigoFilial);
        Response rs = integracaoSalesIntegrationOrder.getDownOrderByCodigoFilial(branchCode);
        if (rs.code() == 200) {
            JSONObject json = new JSONObject();
            json.put("result", rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            rs.close();
            return mapper.readValue(json.getString("result"), DownOrderResponsetDTO.class);
        } else {
            rs.close();
            log.info("Erro ao DownOrderByCodigoFilial" + rs.code());
            return null;
        }
    }

    private DownOrderResponsetDTO getDownOrderResponsetDTO(String warehouseId) throws Exception {
        Response rs = integracaoSalesIntegrationOrder.getDownOrder();
        if (rs.code() == 201) {
            JSONObject json = new JSONObject();
            json.put("result", rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            rs.close();
            List<DownOrderResponsetDTO> downOrder = mapper.readValue(json.getString("result"), new TypeReference<List<DownOrderResponsetDTO>>() {
            });
            Optional<DownOrderResponsetDTO> result = downOrder.stream()
                    .filter(order -> order.getId().equalsIgnoreCase(warehouseId))
                    .findAny();
            if (result.isPresent()) {
                return result.get();
            }
            //throw new Exception("Erro ao DownOrder");
            return null;
        } else {
            rs.close();
            log.info("Erro ao DownOrder" + rs.code());
            //throw new Exception("Erro ao DownOrder");
            return null;
        }
    }

    private PaymentParameterResponsetDTO getPaymentParameter(List<Payment> payment) throws Exception {
        String pay;
        if ("invoiceRequest".equals(payment.get(0).getPaymentType().trim())) {
            pay = payment.get(0).getPaymentType();
        } else {
            pay = payment.get(0).getPaymentType().concat(payment.get(0).getInstallments());
        }

        Response rs = this.integracaoSalesIntegrationOrder.getPaymentParameter();
        if (rs.code() == 200) {
            JSONObject json = new JSONObject();
            json.put("result", rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            rs.close();
            List<PaymentParameterResponsetDTO> downOrder = mapper.readValue(json.getString("result"), new TypeReference<List<PaymentParameterResponsetDTO>>() {
            });
            Optional<PaymentParameterResponsetDTO> result = downOrder.stream()
                    .filter(order -> order.getOccPaymentMethod().trim().equalsIgnoreCase(pay.trim()))
                    .findAny();
            if (result.isPresent()) {
                return result.get();
            }
            return null;
        } else {
            rs.close();
            // throw new Exception("Erro ao PaymentParameter");
            log.info("Erro ao PaymentParameter" + rs.code());
            return null;
        }
    }

    private LowOrderResponsetDTO getLowOrderResponsetDTO() throws Exception {
        Response rs = integracaoSalesIntegrationOrder.getLowOrderResponsetDTO();
        if (rs.code() == 201) {
            JSONObject json = new JSONObject();
            json.put("result", rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            rs.close();
            return mapper.readValue(json.getString("result"), LowOrderResponsetDTO.class);
        } else {
            rs.close();
            throw new Exception("Erro ao LowOrder");
        }
    }

    private ParametersClientRequestDTO getParametersClientRequestDTO() throws Exception {
        Response rs = this.integracaoSalesIntegrationOrder.getParametersClientRequestDTO();
        if (rs.code() == 200) {
            JSONObject json = new JSONObject();
            json.put("result", rs.body().string());
            ObjectMapper mapper = new ObjectMapper();
            rs.close();
            return mapper.readValue(json.getString("result"), ParametersClientRequestDTO.class);
        } else {
            rs.close();
            throw new Exception("Erro ao ParametersClient");
        }
    }

    private Map<String, String> getJsonAndSendSalesIntegrationOrder(OccRequestDTO occRequestDTO,
                                                                    ShippingGroup shippingGroup,
                                                                    ProfileResponseDTO profileResponseDTO) throws Exception {
        String jsonSalesIntegrationOrder = null;
        Response response = null;
        Map<String, String> map = new HashMap<>();
        try {
            PaymentParameterResponsetDTO paymentParameter = getPaymentParameter(occRequestDTO.getPayment());
            DownOrderResponsetDTO downOrderResponsetDTO = getDownOrderResponsetDTO(getBranchCodeByLocation(shippingGroup.getWarehouseId()));
            SalesintegrationorderRequestDTO salesIntegrationOrderRequestDTO = getSalesIntegrationOrderRequestDTO(paymentParameter,
                    getLowOrderResponsetDTO(),
                    downOrderResponsetDTO,
                    occRequestDTO,
                    profileResponseDTO,
                    shippingGroup,
                    getParametersClientRequestDTO());
            salesIntegrationOrderRequestDTO.setSalesIntegrationOrderAddrContract(getOrderAddrContract(shippingGroup));
            jsonSalesIntegrationOrder = mapper.writeValueAsString(salesIntegrationOrderRequestDTO);
            response = integracaoSalesIntegrationOrder.salesIntegrationorder(jsonSalesIntegrationOrder);

        } catch (Exception exception) {
            sendOrder(occRequestDTO, jsonSalesIntegrationOrder, STATUS_CODE_REPROCESS, exception.getMessage(), shippingGroup.getShippingGroupId());
        }
        map.put("jsonSalesIntegrationOrder", jsonSalesIntegrationOrder);
        map.put("result", getResult(response));

        return map;
    }

    private String getResult(Response response) throws IOException, JSONException {
        JSONObject json = new JSONObject();
        json.put("result", response.body().string());

        response.close();
        log.info(json.getString("result"));

        return json.getString("result");
    }

    private String getShippingGroupIdWhenHasError(OccRequestDTO occRequestDTO) {
        return occRequestDTO.getShippingGroups() != null &&
                !occRequestDTO.getShippingGroups().isEmpty() ? occRequestDTO.getShippingGroups().get(0).getShippingGroupId() :
                null;
    }
}