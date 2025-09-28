package com.dev.baquetasOnline.controller;

import com.dev.baquetasOnline.com.BaquetasOnline.model.Product;
import com.dev.baquetasOnline.repository.ProductRepository;
import com.dev.baquetasOnline.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ... (Métodos POST, GET, PUT sin cambios) ...

    // --------------------------------------------------------------------------------------
    // 5. ELIMINAR (DELETE): Retorna 200 OK con mensaje de confirmación
    // DELETE /api/products/{id} -> Retorna 200 OK y JSON de confirmación o 404 Not Found
    // --------------------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    // 👈 CORRECCIÓN AQUÍ: Cambiamos a Map<String, Object> para permitir valores Boolean y String.
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                // Lanza 404 si no lo encuentra.
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. El producto con ID " + id + " no existe."));

        productRepository.delete(product);

        // Creamos el mensaje de confirmación
        Map<String, Object> response = new HashMap<>(); // 👈 Corregido el tipo de mapa
        response.put("deleted", Boolean.TRUE);
        response.put("message", "El producto con ID " + id + " ha sido eliminado exitosamente.");

        // Devuelve 200 OK con el cuerpo JSON
        return ResponseEntity.ok(response);
    }
}