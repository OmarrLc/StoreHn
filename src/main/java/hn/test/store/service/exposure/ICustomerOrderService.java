package hn.test.store.service.exposure;

import hn.test.store.dto.exposure.request.customerOrder.CreateCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.PayCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.UpdateCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.ViewCustomerOrderDetailRestRequestDto;
import hn.test.store.dto.exposure.response.customerOrder.CreateCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.PayCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.UpdateCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.ViewCustomerOrderDetailRestResponseDto;

public interface ICustomerOrderService {

	public CreateCustomerOrderRestResponseDto createCustomerOrder(CreateCustomerOrderRestRequestDto request);
	
	public PayCustomerOrderRestResponseDto payCustomerOrder(PayCustomerOrderRestRequestDto request);
	
	public UpdateCustomerOrderRestResponseDto updateCustomerOrder(UpdateCustomerOrderRestRequestDto request);
	
	public ViewCustomerOrderDetailRestResponseDto viewCuestomerOrderDetail(ViewCustomerOrderDetailRestRequestDto request);
}
