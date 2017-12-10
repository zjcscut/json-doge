package org.throwable.json.exception;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 12:41
 */
public class FastJsonParseException extends RuntimeException {

	public FastJsonParseException() {
		super();
	}

	public FastJsonParseException(String message) {
		super(message);
	}

	public FastJsonParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public FastJsonParseException(Throwable cause) {
		super(cause);
	}
}
