package hn.test.store.service.exposure;

import hn.test.store.dto.base.GeneralResponseDto;
import hn.test.store.dto.exposure.request.authentication.ValidateCredentialRestRequestDto;
import hn.test.store.dto.exposure.response.authentication.ValidateCredentialRestResponseDto;

public interface IAuthenticationService {

	public ValidateCredentialRestResponseDto validateCredential (ValidateCredentialRestRequestDto request);
	
	public GeneralResponseDto validateToken (String token);
}
