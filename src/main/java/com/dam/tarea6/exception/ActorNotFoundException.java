package com.dam.tarea6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 43876691117560211L;

	public ActorNotFoundException(Long id) {
		super("No se puede encontrar el actor con el ID: " + id);
	}

}
