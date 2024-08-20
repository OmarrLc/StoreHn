package hn.test.store.service.exposure.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.test.store.dto.external.response.ProductExternaltResponseItemDto;
import hn.test.store.entity.ProductEntity;
import hn.test.store.service.exposure.IProductService;
import hn.test.store.service.external.IFakeStoreService;
import hn.test.store.util.Utility;

@Service
public class IProductServiceImpl implements IProductService{

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
		
		List<ProductEntity> mapToProductEntities =  util.mapToProductEntities(responseExternal);
		
		if (mapToProductEntities.isEmpty()) {
			return false;
		}
		
		return util.saveAllProduct(mapToProductEntities);
	}

}
