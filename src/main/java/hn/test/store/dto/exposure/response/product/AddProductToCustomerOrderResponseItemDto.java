package hn.test.store.dto.exposure.response.product;

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
public class AddProductToCustomerOrderResponseItemDto {

	private Long orderDetailId;
	List<AdditionalFieldItemDto> additionalFieldColection;
}
