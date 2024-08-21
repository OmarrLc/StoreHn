package hn.test.store.dto.exposure.request.customerOrder;

import java.math.BigDecimal;
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
public class PayCustomerOrderRequestItemDto {

	private Long customerOrderId;
	private String methodPayment;
	private BigDecimal amountTotalReceived;
	List<AdditionalFieldItemDto> additionalFieldColection;
}
