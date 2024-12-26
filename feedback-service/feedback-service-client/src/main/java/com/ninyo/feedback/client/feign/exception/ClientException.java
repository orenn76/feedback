package com.ninyo.feedback.client.feign.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientException extends RuntimeException {

	private HttpStatus status;
	private String code;
	private String message;

	public ClientException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}
}
