package hn.test.store.service.external.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import hn.test.store.dto.external.response.ProductExternaltResponseItemDto;
import hn.test.store.service.external.IFakeStoreService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IFakeStoreServiceImpl implements IFakeStoreService {

	private final RestTemplate restTemplate;

	public IFakeStoreServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ProductExternaltResponseItemDto> getAllProducts() {
		String url = "https://fakestoreapi.com/products";

		List<ProductExternaltResponseItemDto> response = new ArrayList<>();

		try {
			ResponseEntity<List<ProductExternaltResponseItemDto>> responseRest = restTemplate.exchange(url, // URL de tu
																											// API
					HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductExternaltResponseItemDto>>() {
					});

			HttpStatus statusCode = responseRest.getStatusCode();

			if (statusCode.is2xxSuccessful() || statusCode.is4xxClientError()) {
				response = responseRest.getBody();
			} else if (statusCode.is5xxServerError()) {
				log.info("Server Error: " + statusCode);
				return null;
			} else {
				log.info("Invalid Status Code: " + statusCode);
				return null;
			}
		} catch (HttpClientErrorException e) {
			return null;
		}

		return response;
	}

	@Override
	public ProductExternaltResponseItemDto getProductById(Integer productId) {
		String url = "https://fakestoreapi.com/products/" + productId.toString();

		ProductExternaltResponseItemDto response = new ProductExternaltResponseItemDto();

		try {
			ResponseEntity<ProductExternaltResponseItemDto> responseRest = restTemplate.exchange(url, // URL de tu
																											// API
					HttpMethod.GET, null, new ParameterizedTypeReference<ProductExternaltResponseItemDto>() {
					});

			HttpStatus statusCode = responseRest.getStatusCode();

			if (statusCode.is2xxSuccessful() || statusCode.is4xxClientError()) {
				response = responseRest.getBody();
			} else if (statusCode.is5xxServerError()) {
				log.info("Server Error: " + statusCode);
				return null;
			} else {
				log.info("Invalid Status Code: " + statusCode);
				return null;
			}
		} catch (HttpClientErrorException e) {
			return null;
		}

		return response;
	}

}
