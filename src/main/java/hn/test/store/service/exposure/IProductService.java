package hn.test.store.service.exposure;

import hn.test.store.dto.exposure.request.product.AddProductToCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.product.RemoveProductToCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.response.product.AddProductToCustomerOrderRestResponseIDto;
import hn.test.store.dto.exposure.response.product.RemoveProductToCustomerOrderRestResponseDto;

public interface IProductService {

	public Boolean createAllProducts();
	
	public AddProductToCustomerOrderRestResponseIDto addProductToCustomerOrder(AddProductToCustomerOrderRestRequestDto request);
	
	public RemoveProductToCustomerOrderRestResponseDto removeProductToCustomerOrder(RemoveProductToCustomerOrderRestRequestDto request);
}
