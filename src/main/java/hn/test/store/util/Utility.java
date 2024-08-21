package hn.test.store.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hn.test.store.dao.SequenceDao;
import hn.test.store.dto.base.AdditionalFieldItemDto;
import hn.test.store.dto.base.GeneralResponseDto;
import hn.test.store.dto.base.ViewProductDto;
import hn.test.store.dto.exposure.request.customerOrder.PayCustomerOrderRequestItemDto;
import hn.test.store.dto.exposure.response.customerOrder.CreateCustomerOrderResponseItemDto;
import hn.test.store.dto.exposure.response.customerOrder.PayCustomerOrderResponseItemDto;
import hn.test.store.dto.exposure.response.customerOrder.ViewCustomerOrderDetailResponseItemDto;
import hn.test.store.dto.exposure.response.product.AddProductToCustomerOrderResponseItemDto;
import hn.test.store.dto.external.response.ProductExternaltResponseItemDto;
import hn.test.store.entity.CustomerEntity;
import hn.test.store.entity.CustomerOrderEntity;
import hn.test.store.entity.OrderDetailEntity;
import hn.test.store.entity.OrderPaymentEntity;
import hn.test.store.entity.ProductEntity;
import hn.test.store.repository.CustomerRepository;
import hn.test.store.repository.OrderDetailRepository;
import hn.test.store.repository.OrderPaymentRepository;
import hn.test.store.repository.OrderRepository;
import hn.test.store.repository.ProductRepository;

@Component
public class Utility {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CustomerRepository custumerRepo;

	@Autowired
	OrderDetailRepository orderDetailRepo;

	@Autowired
	OrderDetailRepository orderDetailPaymentRepo;

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	OrderPaymentRepository orderPaymentRepo;

	@Autowired
	SequenceDao sequenceDao;

	public Long generateInternalTransactionCode() {
		return sequenceDao.getNextInternalTransactionSequenceValue();
	}

	public Long generateCustomerOderId() {
		return sequenceDao.getNextCustomerOrderSequenceValue();
	}

	public Long generateOrderDetailId() {
		return sequenceDao.getNextOrderDetailSequenceValue();
	}

	public Long generateCustomerId() {
		return sequenceDao.getNextCustomerSequenceValue();
	}

	public Long generateOrderPaymentId() {
		return sequenceDao.getNextOrderPaymentSequenceValue();
	}

	public String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public List<ProductEntity> mapToProductEntities(List<ProductExternaltResponseItemDto> dtoList) {
		return dtoList.stream().map(dto -> {
			ProductEntity entity = new ProductEntity();

			entity.setProductId(dto.getId());
			entity.setProductTitle(dto.getTitle());
			entity.setProductPrice(dto.getPrice());
			entity.setProductCategory(dto.getCategory());
			entity.setProductDescription(dto.getDescription());
			entity.setProductImage(dto.getImage());
			entity.setProductRating(String.valueOf(dto.getRating()));

			entity.setRegisterDate(LocalDate.now().toString());
			entity.setRegisterTime(LocalTime.now().toString());

			entity.setStatusRegister("ACTIVE");

			return entity;
		}).collect(Collectors.toList());
	}

	public Boolean saveAllProduct(List<ProductEntity> products) {

		List<ProductEntity> result = productRepo.saveAll(products);

		return !result.isEmpty();
	}

	public CustomerEntity customerValidation(Long customerId) {

		return custumerRepo.findById(customerId).orElse(null);
	}

	public Long addCustomerOrder(Long customerId) {

		Long customerOrderId = orderRepo
				.save(CustomerOrderEntity.builder().customerOrderId(this.generateCustomerOderId())
						.customerId(customerId).registerDate(LocalDate.now().toString())
						.registerTime(LocalTime.now().toString()).statusRegister("ACTIVE").build())
				.getCustomerOrderId();

		return customerOrderId;
	}

	public GeneralResponseDto mapToGenelResponse(String responseCode, String descriptionResponse) {

		return GeneralResponseDto.builder().internalTransactionCode(this.generateInternalTransactionCode())
				.uniqueResponseCode(this.generateUUID()).responseCode(responseCode)
				.descriptionResponse(descriptionResponse)
				.additionalFieldColection(Arrays.asList(new AdditionalFieldItemDto(1, "KEY", "VALUE"))).build();

	}

	public CreateCustomerOrderResponseItemDto mapToCreateCustomerOrder(Long customerOrderId) {

		return CreateCustomerOrderResponseItemDto.builder().customerOrderId(customerOrderId)
				.registerDate(LocalDate.now().toString()).registerTime(LocalTime.now().toString())
				.additionalFieldColection(Arrays.asList(new AdditionalFieldItemDto(1, "KEY", "VALUE"))).build();
	}

	@Transactional
	public Integer updateCustomerOrder(Long customerOrderId, String statusRegister) {

		return orderRepo.updateById(customerOrderId, statusRegister);
	}

	public CustomerOrderEntity customerOrderValidation(Long customerOrderId) {

		return orderRepo.findByCustomerIdAndStatus(customerOrderId).orElse(null);
	}

	public List<ViewProductDto> getDetailOrder(Long customerOrderId) {

		return orderDetailRepo.getDetailOrder(customerOrderId);
	}

	public BigDecimal calculateOrderTotal(List<ViewProductDto> productList) {

		BigDecimal totalSum = productList.stream()
				.map(detail -> BigDecimal.valueOf(detail.getQuantity() * detail.getProductPrice()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal roundedTotal = totalSum.setScale(2, RoundingMode.HALF_UP);
		return roundedTotal;
	}

	public ViewCustomerOrderDetailResponseItemDto mapToViewCustomerOrder(Long customerOrderId,
			List<ViewProductDto> productColection, BigDecimal customerOrderTotal) {
		return ViewCustomerOrderDetailResponseItemDto.builder().customerOrderId(customerOrderId)
				.productColection(productColection).customerOrderTotal(customerOrderTotal)
				.additionalFieldColection(Arrays.asList(new AdditionalFieldItemDto(1, "KEY", "VALUE"))).build();
	}

	public ProductEntity productValidation(Long productId) {

		return productRepo.findById(productId).orElse(null);
	}

	public OrderDetailEntity orderDetailValidation(Long productId, Long customerOrderId) {

		return orderDetailRepo.findByProductOrder(productId, customerOrderId).orElse(null);
	}

	public Long createOrderDetail(Long productId, Long customerOrderId, Long quantity) {

		return orderDetailRepo.save(
				OrderDetailEntity.builder().orderDetailId(this.generateOrderDetailId()).customerOrderId(customerOrderId)
						.productId(productId).quantity(quantity).registerDate(LocalDate.now().toString())
						.registerTime(LocalTime.now().toString()).statusRegister("ACTIVE").build())
				.getOrderDetailId();
	}

	@Transactional
	public Integer updateQuantityProduct(Long productId, Long customerOrderId, Long quantity) {

		return orderDetailRepo.updateQuantityProduct(quantity, customerOrderId, productId);
	}

	public AddProductToCustomerOrderResponseItemDto mapAddProductToCustomerOrder(Long orderDetailId) {

		return AddProductToCustomerOrderResponseItemDto.builder().orderDetailId(orderDetailId)
				.additionalFieldColection(Arrays.asList(new AdditionalFieldItemDto(1, "KEY", "VALUE"))).build();
	}

	@Transactional
	public Integer removeProductToOrder(Long orderDetailId) {

		return orderDetailRepo.removeProductToOrderId(orderDetailId);
	}

	public PayCustomerOrderResponseItemDto mapPayCustomerOrder(PayCustomerOrderRequestItemDto specificiRequest,
			BigDecimal amountTotalOrder, BigDecimal exchange) {

		return PayCustomerOrderResponseItemDto.builder().customerOrderId(specificiRequest.getCustomerOrderId())
				.methodPayment(specificiRequest.getMethodPayment()).amountTotalOrder(amountTotalOrder)
				.amountTotalReceived(specificiRequest.getAmountTotalReceived()).exchange(exchange)
				.registerDate(LocalDate.now().toString()).registerTime(LocalTime.now().toString())
				.additionalFieldColection(Arrays.asList(new AdditionalFieldItemDto(1, "KEY", "VALUE"))).build();
	}

	public OrderPaymentEntity creatOrderPayment(PayCustomerOrderRequestItemDto specificiRequest,
			BigDecimal amountTotalOrder, BigDecimal exchange) {

		return orderPaymentRepo.save(OrderPaymentEntity.builder().orderPaymentId(this.generateOrderPaymentId())
				.customerOrderEntityId(specificiRequest.getCustomerOrderId())
				.methodPayment(specificiRequest.getMethodPayment()).amountTotalOrder(amountTotalOrder)
				.amountTotalReceived(specificiRequest.getAmountTotalReceived()).exchange(exchange)
				.registerDate(LocalDate.now().toString()).registerTime(LocalTime.now().toString())
				.statusRegister("ACTIVE").build());
	}
	
}
