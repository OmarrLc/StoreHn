package hn.test.store.dto.exposure.request.product;

import hn.test.store.dto.base.GeneralRequestDto;
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
public class RemoveProductToCustomerOrderRestRequestDto {

	private GeneralRequestDto generalRequest;
	private RemoveProductToCustomerOrderRequesItemDto removeProductToCustomerOrder;
}
