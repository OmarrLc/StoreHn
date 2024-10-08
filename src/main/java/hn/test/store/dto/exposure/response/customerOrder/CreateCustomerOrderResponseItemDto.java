package hn.test.store.dto.exposure.response.customerOrder;

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
public class CreateCustomerOrderResponseItemDto {

	private Long customerOrderId;
	private String registerDate;
	private String registerTime;
	List<AdditionalFieldItemDto> additionalFieldColection;
}
