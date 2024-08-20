package hn.test.store.service.external;

import java.util.List;

import hn.test.store.dto.external.response.ProductExternaltResponseItemDto;

public interface IFakeStoreService {
	
	List<ProductExternaltResponseItemDto> getAllProducts();
	
	ProductExternaltResponseItemDto getProductById(Integer prodcutId);

}
