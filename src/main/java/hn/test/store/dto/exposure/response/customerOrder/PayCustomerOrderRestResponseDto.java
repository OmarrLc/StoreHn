package hn.test.store.dto.exposure.response.customerOrder;

import java.util.List;

import hn.test.store.dto.base.AdditionalFieldItemDto;
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
public class PayCustomerOrderRestResponseDto {
	
	private GeneralResponseDto generalResponse;
	List<AdditionalFieldItemDto> additionalFieldColection;
}
