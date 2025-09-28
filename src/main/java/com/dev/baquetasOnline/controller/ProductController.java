package com.dev.baquetasOnline.controller;

import com.dev.baquetasOnline.com.BaquetasOnline.model.Product;
import com.dev.baquetasOnline.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // 1. Marca la clase para devolver datos JSON/XML (NO vistas)
@RequestMapping("/api/products") // 2. Define la ruta base para todos los endpoints (e.g., /api/products)
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {

    // 4. Inyección del Repositorio
    // Spring inyectará automáticamente una instancia del ProductRepository que creaste.
    @Autowired
    private ProductRepository productRepository;

    // ===============================================
    //               Métodos CRUD
    // ===============================================

    // [C]REAR: POST /api/products
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Usa el repositorio para guardar el nuevo objeto Product en la BD.
        return productRepository.save(product);
    }

    // [R]EAD ALL: GET /api/products
    @GetMapping
    public List<Product> getAllProducts() {
        // Usa el repositorio para encontrar y devolver la lista de todos los productos.
        return productRepository.findAll();
    }

    // [R]EAD ONE: GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Busca el producto. Si existe, devuelve 200 OK y el producto.
        // Si no existe, devuelve 404 NOT FOUND.
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // [U]PDATE: PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    // Actualiza los campos existentes con los detalles recibidos
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setImageUrl(productDetails.getImageUrl());
                    product.setImageAlt(productDetails.getImageAlt());

                    Product updatedProduct = productRepository.save(product);
                    return ResponseEntity.ok(updatedProduct);
                }).orElse(ResponseEntity.notFound().build());
    }

    // [D]ELETE: DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product); // Elimina el producto
                    return ResponseEntity.ok().build(); // Devuelve 200 OK (sin contenido)
                }).orElse(ResponseEntity.notFound().build());
    }

}
