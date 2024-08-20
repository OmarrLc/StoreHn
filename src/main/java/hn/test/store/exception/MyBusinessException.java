package hn.test.store.exception;


public class MyBusinessException extends Exception{
	
	private static final long serialVersionUID = 8285945485456669356L;

	private String responseCode;

	public MyBusinessException() {
		super();
	}

	public String getCodigoRespuestaValid() {
		return responseCode;
	}

	public void setCodigoRespuestaValid(String responseCode) {
		this.responseCode = responseCode;
	}

	public MyBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MyBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyBusinessException(String message) {
		super(message);
	}

	public MyBusinessException(Throwable cause) {
		super(cause);
	}

	public MyBusinessException(String message, String responseCode) {
		super(message);
		this.responseCode = responseCode;
	}


}
