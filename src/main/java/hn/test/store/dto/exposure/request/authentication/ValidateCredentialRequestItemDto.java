package hn.test.store.dto.exposure.request.authentication;

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
public class ValidateCredentialRequestItemDto {
	
	private String customerName;
	List<AdditionalFieldItemDto> additionalFieldColection;

}
