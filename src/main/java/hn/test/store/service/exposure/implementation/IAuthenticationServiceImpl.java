package hn.test.store.service.exposure.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.test.store.dto.base.GeneralResponseDto;
import hn.test.store.dto.exposure.request.authentication.ValidateCredentialRequestItemDto;
import hn.test.store.dto.exposure.request.authentication.ValidateCredentialRestRequestDto;
import hn.test.store.dto.exposure.response.authentication.ValidateCredentialResponseItemDto;
import hn.test.store.dto.exposure.response.authentication.ValidateCredentialRestResponseDto;
import hn.test.store.entity.CustomerEntity;
import hn.test.store.exception.MyBusinessException;
import hn.test.store.jwt.JwtImpl;
import hn.test.store.service.exposure.IAuthenticationService;
import hn.test.store.util.Utility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IAuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	Utility util;

	@Autowired
	JwtImpl jwt;

	@Override
	public ValidateCredentialRestResponseDto validateCredential(ValidateCredentialRestRequestDto request) {

		ValidateCredentialRequestItemDto specificiRequest = request.getValidateCredential();
		GeneralResponseDto generalResponse = null;
		ValidateCredentialResponseItemDto especificResponse = null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		String customerName = specificiRequest.getCustomerName();
		Long customerId = 0L;
		String token = null;

		try {

			CustomerEntity customer = util.customerValidationByName(customerName);

			if (customer == null) {
				responseCode = "01";
				descriptionResponse = "INVALID CUSTOMER";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}

			customerId = customer.getCustomerId();

			token = jwt.generateToken(customerId, customerName);

		} catch (MyBusinessException eb) {
			responseCode = eb.getCodigoRespuestaValid();
			descriptionResponse = eb.getMessage();
			log.warn(eb.getMessage());
		} catch (Exception ex) {
			responseCode = "99";
			descriptionResponse = "CONTROLLED ERROR";
			log.error(ex.getMessage());
		}

		generalResponse = util.mapToGenelResponse(responseCode, descriptionResponse);

		if (responseCode.equals("00")) {
			especificResponse = util.mapValidateCredential(customerId, customerName, token);
		}

		return ValidateCredentialRestResponseDto.builder().generalResponse(generalResponse)
				.validateCredential(especificResponse).build();
	}

	@Override
	public GeneralResponseDto validateToken(String token) {
		GeneralResponseDto generalResponse = null;
		String responseCode = "00";
		String descriptionResponse = "SUCCESSFUL";
		
		try {

			if (token == null || token.isEmpty()) {
				responseCode = "01";
				descriptionResponse = "EMPTY TOKEN";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}
			
			if (jwt.isTokenExpired(token)) {
				responseCode = "02";
				descriptionResponse = "EXPIRED TOKEN";
				throw new MyBusinessException(descriptionResponse, responseCode);
			}

		} catch (MyBusinessException eb) {
			responseCode = eb.getCodigoRespuestaValid();
			descriptionResponse = eb.getMessage();
			log.warn(eb.getMessage());
		} catch (Exception ex) {
			
			if (ex.getMessage().startsWith("JWT expired")) {
				responseCode = "03";
				descriptionResponse = "EXPIRED TOKEN";
				log.error(ex.getMessage());;
			}else if (ex.getMessage().startsWith("Malformed JWT") || ex.getMessage().startsWith("JWT signature does not match locally")) {
				responseCode = "04";
				descriptionResponse = "INVALID TOKEN";
				log.error(ex.getMessage());
			} else {
				responseCode = "99";
				descriptionResponse = "CONTROLLED ERROR";
				log.error(ex.getMessage());
			}
			
		}

		generalResponse = util.mapToGenelResponse(responseCode, descriptionResponse);

		return generalResponse;
	}

}
