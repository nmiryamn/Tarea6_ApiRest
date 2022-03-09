package com.dam.tarea6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 43876691117560211L;

	public PeliculaNotFoundException(Long id) {
		super("No se puede encontrar la película con el ID: " + id);
	}

}
