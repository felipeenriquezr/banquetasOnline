package com.dev.baquetasOnline.controller;

import com.dev.baquetasOnline.com.BaquetasOnline.model.Product;
import com.dev.baquetasOnline.repository.ProductRepository;
import com.dev.baquetasOnline.exception.ResourceNotFoundException; // Importado
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

    // --------------------------------------------------------------------------------------
    // 1. CREAR (CREATE): NO SE AFECTA POR EL MANEJO DE EXCEPCIONES.
    // POST /api/products -> Retorna 201 Created
    // --------------------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        // Retorna el producto guardado con el código 201 Created.
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // --------------------------------------------------------------------------------------
    // 2. LEER TODOS (READ ALL): NO SE AFECTA.
    // GET /api/products -> Retorna 200 OK con la lista
    // --------------------------------------------------------------------------------------
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // --------------------------------------------------------------------------------------
    // 3. LEER POR ID (READ BY ID): MANEJA EXCEPCIÓN.
    // GET /api/products/{id} -> Retorna 200 OK o 404 Not Found
    // --------------------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                // Lanza 404 si no lo encuentra.
                .orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + id + " no existe."));

        return ResponseEntity.ok(product); // 200 OK si lo encuentra.
    }

    // --------------------------------------------------------------------------------------
    // 4. ACTUALIZAR (UPDATE): MANEJA EXCEPCIÓN SOLO SI NO ENCUENTRA EL ID.
    // PUT /api/products/{id} -> Retorna 200 OK o 404 Not Found
    // --------------------------------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {

        Product product = productRepository.findById(id)
                // Lanza 404 si no lo encuentra.
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. El producto con ID " + id + " no existe."));

        // Si el recurso EXISTE, el código continúa aquí y devuelve 200 OK
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setImageUrl(productDetails.getImageUrl());
        product.setImageAlt(productDetails.getImageAlt());

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct); // 200 OK
    }

    // --------------------------------------------------------------------------------------
    // 5. ELIMINAR (DELETE): MANEJA EXCEPCIÓN SOLO SI NO ENCUENTRA EL ID.
    // DELETE /api/products/{id} -> Retorna 204 No Content o 404 Not Found
    // --------------------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    // 👈 Cambiamos el tipo de retorno para devolver un Map (JSON)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                // Lanza 404 si no lo encuentra.
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. El producto con ID " + id + " no existe."));

        // Mensaje que quieres enviar
        String confirmationMessage = "El producto con ID " + id + " ha sido eliminado exitosamente.";

        // 1. Elimina el producto de la base de datos
        productRepository.delete(product);

        // 2. Devuelve 204 No Content, agregando el mensaje a una cabecera personalizada (X-Confirmation)
        return ResponseEntity.noContent()
                .header("X-Confirmation", confirmationMessage) // Agrega el mensaje a la cabecera
                .<Void>build();
    }
}