package hn.test.store.service.exposure.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.test.store.dto.base.GeneralResponseDto;
import hn.test.store.dto.base.ViewProductDto;
import hn.test.store.dto.exposure.request.customerOrder.CreateCustomerOrderIRequestItemDto;
import hn.test.store.dto.exposure.request.customerOrder.CreateCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.PayCustomerOrderRequestItemDto;
import hn.test.store.dto.exposure.request.customerOrder.PayCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.UpdateCustomerOrderRequestItemDto;
import hn.test.store.dto.exposure.request.customerOrder.UpdateCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.ViewCustomerOrderDetailRequestItemDto;
import hn.test.store.dto.exposure.request.customerOrder.ViewCustomerOrderDetailRestRequestDto;
import hn.test.store.dto.exposure.response.customerOrder.CreateCustomerOrderResponseItemDto;
import hn.test.store.dto.exposure.response.customerOrder.CreateCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.PayCustomerOrderResponseItemDto;
import hn.test.store.dto.exposure.response.customerOrder.PayCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.UpdateCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.ViewCustomerOrderDetailResponseItemDto;
import hn.test.store.dto.exposure.response.customerOrder.ViewCustomerOrderDetailRestResponseDto;
import hn.test.store.entity.CustomerEntity;
import hn.test.store.entity.CustomerOrderEntity;
import hn.test.store.entity.OrderPaymentEntity;
import hn.test.store.exception.MyBusinessException;
import hn.test.store.service.exposure.ICustomerOrderService;
import hn.test.store.util.Utility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ICustomerOrderServiceImpl implements ICustomerOrderService{

	@Autowired
	Utility util;
	
	@Override
	public CreateCustomerOrderRestResponseDto createCustomerOrder(CreateCustomerOrderRestRequestDto request) {
		
		CreateCustomerOrderIRequestItemDto specificiRequest = request.getCreateCustomerOrder();
		GeneralResponseDto generalResponse = null;
		CreateCustomerOrderResponseItemDto especificResponse =  null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		Long customerId = specificiRequest.getCustomerId();
		Long customerOrderId = 0L;
		
		try {
			
			CustomerEntity customer = util.customerValidation(customerId);
			
			if (customer == null){
				responseCode = "01";
				descriptionResponse = "INVALID CUSTOMER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			customerOrderId = util.addCustomerOrder(customerId);
			
			if (customerOrderId == 0L){
				responseCode = "02";
				descriptionResponse = "ERROR CREATING CUSTOMER ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
		} catch (MyBusinessException eb) {
			responseCode = eb.getCodigoRespuestaValid();
			descriptionResponse = eb.getMessage();
			log.warn(eb.getMessage());
		} catch (Exception ex) {
			responseCode = "99";
			descriptionResponse = "CONTROLLED ERROR";
			log.error(ex.getMessage());
		}
		
		generalResponse = util.mapToGenelResponse(responseCode, descriptionResponse);
		
		if (responseCode.equals("00")) {
			especificResponse = util.mapToCreateCustomerOrder(customerOrderId);
		}
		
		return CreateCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).createCustomerOrder(especificResponse).build();
	}

	@Override
	public PayCustomerOrderRestResponseDto payCustomerOrder(PayCustomerOrderRestRequestDto request) {
		PayCustomerOrderRequestItemDto specificiRequest = request.getPayCustomerOrder();
		GeneralResponseDto generalResponse = null;
		PayCustomerOrderResponseItemDto especificResponse =  null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		Long customerOrderId = specificiRequest.getCustomerOrderId();
		BigDecimal amountReceived = specificiRequest.getAmountTotalReceived();
		BigDecimal totalOrder = new BigDecimal(0.0);
		BigDecimal exchange = new BigDecimal(0.0);
		List<ViewProductDto> detailProductOrder = null;
		String statusRegister = "PAID";
		
		try {
			
			CustomerOrderEntity customerOrder = util.customerOrderValidation(customerOrderId);
			
			if (customerOrder == null){
				responseCode = "01";
				descriptionResponse = "INVALID CUSTOMER ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			detailProductOrder = util.getDetailOrder(customerOrderId);
			
			if (detailProductOrder.isEmpty()) {
				responseCode = "02";
				descriptionResponse = "EMPTY ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			totalOrder = util.calculateOrderTotal(detailProductOrder);
			
			if (amountReceived.compareTo(totalOrder) < 0) {
				responseCode = "03";
				descriptionResponse = "AMOUNT ENTERED IS LESS THAN THE ORDER TOTAL";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			exchange = amountReceived.subtract(totalOrder);
			
			OrderPaymentEntity createOrderPayment = util.creatOrderPayment(specificiRequest, totalOrder, exchange);
			
			if (createOrderPayment == null){
				responseCode = "04";
				descriptionResponse = "ERROR CREATING PAYMENT ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			Integer flagUpdate = util.updateCustomerOrder(customerOrderId,statusRegister);
			
			if (flagUpdate == 0){
				responseCode = "05";
				descriptionResponse = "ERROR UPDATING CUSTOMER ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
		} catch (MyBusinessException eb) {
			responseCode = eb.getCodigoRespuestaValid();
			descriptionResponse = eb.getMessage();
			log.warn(eb.getMessage());
		} catch (Exception ex) {
			responseCode = "99";
			descriptionResponse = "CONTROLLED ERROR";
			log.error(ex.getMessage());
		}
		
		generalResponse = util.mapToGenelResponse(responseCode, descriptionResponse);
		
		if (responseCode.equals("00")) {
			especificResponse = util.mapPayCustomerOrder(specificiRequest, totalOrder, exchange);
		}
		
		return PayCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).payCustomerOrder(especificResponse).build();
	}

	@Override
	public UpdateCustomerOrderRestResponseDto updateCustomerOrder(UpdateCustomerOrderRestRequestDto request) {
		UpdateCustomerOrderRequestItemDto specificiRequest = request.getUpdateCustomerOrder();
		GeneralResponseDto generalResponse = null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		Long customerOrderId = specificiRequest.getCustomerOrderId();
		String statusRegister = specificiRequest.getStatusRegister();
		
		try {
			
			Integer flagUpdate = util.updateCustomerOrder(customerOrderId,statusRegister);
			
			if (flagUpdate == 0){
				responseCode = "01";
				descriptionResponse = "ERROR UPDATING CUSTOMER ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
		} catch (MyBusinessException eb) {
			responseCode = eb.getCodigoRespuestaValid();
			descriptionResponse = eb.getMessage();
			log.warn(eb.getMessage());
		} catch (Exception ex) {
			responseCode = "99";
			descriptionResponse = "CONTROLLED ERROR";
			log.error(ex.getMessage());
		}
		
		generalResponse = util.mapToGenelResponse(responseCode, descriptionResponse);
		
		return UpdateCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).build();
	}

	@Override
	public ViewCustomerOrderDetailRestResponseDto viewCuestomerOrderDetail(
			ViewCustomerOrderDetailRestRequestDto request) {
		
		ViewCustomerOrderDetailRequestItemDto specificiRequest = request.getViewCustomerOrder();
		GeneralResponseDto generalResponse = null;
		ViewCustomerOrderDetailResponseItemDto especificResponse =  null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		Long customerOrderId = specificiRequest.getCustomerOrderId();
		BigDecimal totalOrder = new BigDecimal(0.0);
		List<ViewProductDto> detailProductOrder = null;
		
		try {
			
			CustomerOrderEntity customerOrder = util.customerOrderValidation(customerOrderId);
			
			if (customerOrder == null){
				responseCode = "01";
				descriptionResponse = "INVALID CUSTOMER ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			detailProductOrder = util.getDetailOrder(customerOrderId);
			
			if (detailProductOrder.isEmpty()) {
				responseCode = "02";
				descriptionResponse = "EMPTY ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			totalOrder = util.calculateOrderTotal(detailProductOrder);
			
		} catch (MyBusinessException eb) {
			responseCode = eb.getCodigoRespuestaValid();
			descriptionResponse = eb.getMessage();
			log.warn(eb.getMessage());
		} catch (Exception ex) {
			responseCode = "99";
			descriptionResponse = "CONTROLLED ERROR";
			log.error(ex.getMessage());
		}
		
		generalResponse = util.mapToGenelResponse(responseCode, descriptionResponse);
		
		if (responseCode.equals("00")) {
			especificResponse = util.mapToViewCustomerOrder(customerOrderId, detailProductOrder, totalOrder );
		}
		
		return ViewCustomerOrderDetailRestResponseDto.builder().generalResponse(generalResponse).viewCustomerOrder(especificResponse).build();
	}

}
