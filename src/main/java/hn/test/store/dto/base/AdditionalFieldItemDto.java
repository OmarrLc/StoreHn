package hn.test.store.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class AdditionalFieldItemDto {
	
	@Schema(description = "Item Number", example = "1")
	private Integer itemNumber;
	
	@Schema(description = "Value Key", example = "Key")
	private String valueKey;
	
	@Schema(description = "Value", example = "Value")
	private String value;

}
