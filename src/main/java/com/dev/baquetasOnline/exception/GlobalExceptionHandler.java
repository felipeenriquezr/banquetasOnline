package com.dev.baquetasOnline.exception;

import com.dev.baquetasOnline.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Indica que esta clase manejará excepciones de varios controladores.
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        // Crea un cuerpo de respuesta JSON personalizado.
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage()); // Captura el mensaje de la excepción (ej: "Producto no existe.")
        body.put("path", request.getDescription(false).substring(4)); // Captura la ruta de la solicitud.

        // Devuelve la respuesta con código 404 y el cuerpo JSON.
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}