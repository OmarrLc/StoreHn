package hn.test.store.dto.exposure.response.customerOrder;

import java.math.BigDecimal;
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
	
	private Long customerOrderId;
	private List<ViewProductDto> productColection;
	private BigDecimal customerOrderTotal;
	List<AdditionalFieldItemDto> additionalFieldColection;

}
