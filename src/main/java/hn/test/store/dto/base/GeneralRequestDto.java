package hn.test.store.dto.base;

import java.util.List;

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
public class GeneralRequestDto {
	
	@Schema(description = "Unique Request Code", example = "76bf8fdf-9068-4a05-9879-56845219c29e")
	private String uniqueRequestCode;
	
	@Schema(description = "Channel Id", example = "1")
	private String channelId;
	
	@Schema(description = "Transaction Id", example = "1")
	private String transactionId;
	
	List<AdditionalFieldItemDto> additionalFieldColection;

}
