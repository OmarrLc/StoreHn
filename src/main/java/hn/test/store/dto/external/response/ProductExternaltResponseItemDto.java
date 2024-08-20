package hn.test.store.dto.external.response;

import hn.test.store.dto.base.RatingDto;
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
public class ProductExternaltResponseItemDto {

	private Integer id;
	private String title;
	private Long price;
	private String description;
	private String category;
	private String image;
	private RatingDto rating;
}
