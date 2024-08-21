package hn.test.store.dto.exposure.response.authentication;

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
public class ValidateCredentialResponseItemDto {
	
	private Long customerId;
	private String customerName;
	private String token;
	List<AdditionalFieldItemDto> additionalFieldColection;
}
