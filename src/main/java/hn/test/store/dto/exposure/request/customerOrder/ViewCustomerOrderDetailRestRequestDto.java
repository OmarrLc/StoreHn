package hn.test.store.dto.exposure.request.customerOrder;

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
public class ViewCustomerOrderDetailRestRequestDto {

	private GeneralRequestDto generalRequest;
	private ViewCustomerOrderDetailRequestItemDto viewCustomerOrder;
}
