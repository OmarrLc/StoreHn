package hn.test.store.service.exposure.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.test.store.dto.base.GeneralResponseDto;
import hn.test.store.dto.exposure.request.product.AddProductToCustomerOrderRequestItemDto;
import hn.test.store.dto.exposure.request.product.AddProductToCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.product.RemoveProductToCustomerOrderRequesItemDto;
import hn.test.store.dto.exposure.request.product.RemoveProductToCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.response.product.AddProductToCustomerOrderResponseItemDto;
import hn.test.store.dto.exposure.response.product.AddProductToCustomerOrderRestResponseIDto;
import hn.test.store.dto.exposure.response.product.RemoveProductToCustomerOrderRestResponseDto;
import hn.test.store.dto.external.response.ProductExternaltResponseItemDto;
import hn.test.store.entity.CustomerOrderEntity;
import hn.test.store.entity.OrderDetailEntity;
import hn.test.store.entity.ProductEntity;
import hn.test.store.exception.MyBusinessException;
import hn.test.store.service.exposure.IProductService;
import hn.test.store.service.external.IFakeStoreService;
import hn.test.store.util.Utility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IProductServiceImpl implements IProductService {

	@Autowired
	IFakeStoreService externalProductService;

	@Autowired
	Utility util;

	@Override
	public Boolean createAllProducts() {

		List<ProductExternaltResponseItemDto> responseExternal = externalProductService.getAllProducts();

		if (responseExternal.isEmpty()) {
			return false;
		}

		List<ProductEntity> mapToProductEntities = util.mapToProductEntities(responseExternal);

		if (mapToProductEntities.isEmpty()) {
			return false;
		}

		return util.saveAllProduct(mapToProductEntities);
	}

	@Override
	public AddProductToCustomerOrderRestResponseIDto addProductToCustomerOrder(
			AddProductToCustomerOrderRestRequestDto request) {
		AddProductToCustomerOrderRequestItemDto specificiRequest = request.getAddProductToCustomerOrder();
		AddProductToCustomerOrderResponseItemDto specificResponse = null;
		GeneralResponseDto generalResponse = null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		Long customerOrderId = specificiRequest.getCustomerOrderId();
		Long productId = specificiRequest.getProductId();
		Long quantity = specificiRequest.getQuantity();
		Long orderDetailId = 0L;

		try {

			CustomerOrderEntity customerOrder = util.customerOrderValidation(customerOrderId);

			if (customerOrder == null) {
				responseCode = "01";
				descriptionResponse = "INVALID CUSTOMER ORDER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}

			ProductEntity product = util.productValidation(productId);

			if (product == null) {
				responseCode = "02";
				descriptionResponse = "INVALID PRODUCT";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}

			OrderDetailEntity orderDetail = util.orderDetailValidation(productId, customerOrderId);

			if (orderDetail == null) {

				orderDetailId = util.createOrderDetail(productId, customerOrderId, quantity);

				if (orderDetailId == 0L) {
					responseCode = "03";
					descriptionResponse = "ERROR ADDING PRODUCT TO SHOPPING CART";
					throw new MyBusinessException(descriptionResponse, responseCode);
				}
			} else {
				orderDetailId = orderDetail.getOrderDetailId();
				Integer updateFlag = util.updateQuantityProduct(productId, customerOrderId, quantity);

				if (updateFlag == 0) {
					responseCode = "04";
					descriptionResponse = "ERROR ADDING PRODUCT TO SHOPPING CART";
					throw new MyBusinessException(descriptionResponse, responseCode);
				}
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
			specificResponse = util.mapAddProductToCustomerOrder(orderDetailId);
		}

		return AddProductToCustomerOrderRestResponseIDto.builder().generalResponse(generalResponse)
				.adddProductToCustomerOrder(specificResponse).build();
	}

	@Override
	public RemoveProductToCustomerOrderRestResponseDto removeProductToCustomerOrder(
			RemoveProductToCustomerOrderRestRequestDto request) {
		RemoveProductToCustomerOrderRequesItemDto specificiRequest = request.getRemoveProductToCustomerOrder();
		GeneralResponseDto generalResponse = null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		Long orderDetailId = specificiRequest.getOrderDetailId();

		try {

			Integer flagRemove = util.removeProductToOrder(orderDetailId);
			
			if (flagRemove == 0) {
				responseCode = "01";
				descriptionResponse = "ERROR REMOVING PRODUCT TO SHOPPING CART";
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

		return RemoveProductToCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).build();
	}

}
