package hn.test.store.dto.exposure.response.product;

import hn.test.store.dto.base.GeneralResponseDto;
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
public class RemoveProductToCustomerOrderRestResponseDto {

	private GeneralResponseDto generalResponse;
}
