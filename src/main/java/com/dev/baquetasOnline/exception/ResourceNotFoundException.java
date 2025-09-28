package com.dev.baquetasOnline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotación indica que, si esta excepción se lanza, Spring debe responder con el código HTTP 404 NOT FOUND.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Constructor que acepta un mensaje para la respuesta.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}