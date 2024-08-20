package hn.test.store.dto.base;

import java.util.List;

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
public class GeneralResponseDto {
	
	private String internalTransactionCode;
	private String uniqueResponseCode;
	private String responseCode;
	private String descriptionResponse;
	
	List<AdditionalFieldItemDto> additionalFieldColection;

}
