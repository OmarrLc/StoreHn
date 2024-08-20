package hn.test.store.configuration;

import org.springframework.context.annotation.Configuration;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import hn.test.store.exception.CustomResponseErrorHandler;
import hn.test.store.util.Utility;

@Configuration
public class RestTemplateConfig {
	
	@Autowired
	Utility util;
	
	@Autowired
	MyPropertiesConfig myProperties;
	
	@Bean
	RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(30000);
		factory.setReadTimeout(50000);

		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setErrorHandler(new CustomResponseErrorHandler());

		return restTemplate;
	}

	@Bean
	public RestTemplate restTemplateSsl(RestTemplateBuilder builder)
			throws NoSuchAlgorithmException, KeyManagementException, CertificateException {
		
		String pemCertX3 = myProperties.getCertificate().trim();
		
		String pemCert = util.base64Decode3R(pemCertX3);

		X509Certificate certificate = util.loadCertificateFromPem(pemCert);

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[] { new X509ExtendedTrustManager() {

			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
			}

			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				for (X509Certificate cert : x509Certificates) {
					if (cert.equals(certificate)) {
						return;
					}
				}
				throw new CertificateException("Untrusted certificate");
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] { certificate };
			}

			public void checkClientTrusted(X509Certificate[] x509Certificates, String s, java.net.Socket socket) {
			}

			public void checkServerTrusted(X509Certificate[] x509Certificates, String s, java.net.Socket socket) {
			}

			public void checkClientTrusted(X509Certificate[] x509Certificates, String s,
					javax.net.ssl.SSLEngine sslEngine) {
			}

			public void checkServerTrusted(X509Certificate[] x509Certificates, String s,
					javax.net.ssl.SSLEngine sslEngine) {
			}
		} }, null);

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
				org.apache.http.impl.client.HttpClients.custom().setSSLContext(sslContext).build());

		return builder.requestFactory(() -> factory).build();
	}
}
