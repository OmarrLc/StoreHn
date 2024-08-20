package hn.test.store.util;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hn.test.store.dto.external.response.ProductExternaltResponseItemDto;
import hn.test.store.entity.ProductEntity;
import hn.test.store.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Utility {
	public static final String MENSAJE_EXCEPCION_1 = "No se puede codificar cadena de caracteres nula o vacia";
	public static final String MENSAJE_EXCEPCION_2 = "Error codificando base64: ";
	
	@Autowired
	ProductRepository productRepo;
	
	public String base64Decode3R(String strDecode) {
		try {
			if (strDecode == null || strDecode.length() <= 0 || strDecode.isEmpty()) {
				throw new Exception(MENSAJE_EXCEPCION_1);
			}
			String x1 = new String(Base64.getDecoder().decode(strDecode.getBytes(StandardCharsets.UTF_8)));
			String x2 = new String(Base64.getDecoder().decode(x1.getBytes(StandardCharsets.UTF_8)));
			return new String(Base64.getDecoder().decode(x2.getBytes(StandardCharsets.UTF_8)));

		} catch (Exception e) {
			log.error(MENSAJE_EXCEPCION_2 + e.getMessage());
		}
		return null;
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
}
