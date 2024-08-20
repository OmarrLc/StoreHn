package hn.test.store.util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Utility {
	public static final String MENSAJE_EXCEPCION_1 = "No se puede codificar cadena de caracteres nula o vacia";
	public static final String MENSAJE_EXCEPCION_2 = "Error codificando base64: ";
	
	public String base64Decode3R(String strDecode) {
		try {
			if (strDecode == null || strDecode.length() <= 0 || strDecode.isEmpty()) {
				throw new Exception(MENSAJE_EXCEPCION_1);
			}
			String x1 = new String(Base64.getDecoder().decode(strDecode.getBytes(StandardCharsets.UTF_8)));
			String x2 = new String(Base64.getDecoder().decode(x1.getBytes(StandardCharsets.UTF_8)));
			return new String(Base64.getDecoder().decode(x2.getBytes(StandardCharsets.UTF_8)));

		} catch (Exception e) {
			log.error(MENSAJE_EXCEPCION_2 + e.getMessage());
		}
		return null;
	}

	public X509Certificate loadCertificateFromPem(String pem) throws CertificateException {
		String base64 = pem.replace("-----BEGIN CERTIFICATE-----", "").replace("-----END CERTIFICATE-----", "")
				.replaceAll("\\s", "");

		byte[] decoded = Base64.getDecoder().decode(base64);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(decoded));
	}
}
