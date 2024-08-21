package hn.test.store.exception;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(@NonNull ClientHttpResponse response) throws IOException {
		return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
	}

	@Override
	public void handleError(@NonNull ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().is4xxClientError()) {
			log.error("Client Error: " + response.getStatusCode());
		} else if (response.getStatusCode().is5xxServerError()) {
			log.error("Server Error: " + response.getStatusCode());
		} else {
			log.error("Error: " + response.getStatusCode());
		}
	}
}
