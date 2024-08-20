package hn.test.store.dto.exposure.response.customerOrder;

import java.util.List;

import hn.test.store.dto.base.AdditionalFieldItemDto;
import hn.test.store.dto.base.ViewProductDto;
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
public class ViewCustomerOrderDetailResponseItemDto {
	
	private Long customerId;
	private Long customerOrderId;
	private List<ViewProductDto> productColection;
	private Double customerOrderTotal;
	List<AdditionalFieldItemDto> additionalFieldColection;

}
