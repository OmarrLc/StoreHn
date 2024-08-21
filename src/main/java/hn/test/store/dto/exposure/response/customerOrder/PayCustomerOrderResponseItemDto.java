package hn.test.store.dto.exposure.response.customerOrder;

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
public class PayCustomerOrderResponseItemDto {
	
	private Long customerOrderId;
	private String methodPayment;
	private BigDecimal amountTotalOrder;
	private BigDecimal amountTotalReceived;
	private BigDecimal exchange;
	private String registerDate;
	private String registerTime;
	List<AdditionalFieldItemDto> additionalFieldColection;

}
