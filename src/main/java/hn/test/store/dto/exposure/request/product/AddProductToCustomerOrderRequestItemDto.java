package hn.test.store.dto.exposure.request.product;

import java.util.List;

import hn.test.store.dto.base.AdditionalFieldItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddProductToCustomerOrderRequestItemDto {

	private Long customerOrderId;
	private Long productId;
	private Long quantity;
	List<AdditionalFieldItemDto> additionalFieldColection;
}
