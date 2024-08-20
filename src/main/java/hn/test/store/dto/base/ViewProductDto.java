package hn.test.store.dto.base;

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
public class ViewProductDto {

	private Long orderDetailId;
	private Long productId;
	private String productTitle;
	private Double productPrice;
	private Long quantity;
	private Double totalByProduct;
}
