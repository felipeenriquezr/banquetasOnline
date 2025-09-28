package com.dev.baquetasOnline.repository;

import com.dev.baquetasOnline.com.BaquetasOnline.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long>  {
    // El 'JpaRepository' toma dos parámetros genéricos:
    // 1. Product: La clase de la Entidad (el modelo) que vamos a gestionar.
    // 2. Long: El tipo de dato de la clave primaria (ID) de esa entidad (idProduct es Long).


    // ----------------------------------------------------------------------------------
    // Métodos Heredados:
    // - save(Product): Guarda o actualiza un producto.
    // - findById(Long): Busca un producto por su ID.
    // - findAll(): Devuelve una lista de todos los productos.
    // - delete(Product): Elimina un producto.


    // ----------------------------------------------------------------------------------
    // Opcional: Puedes añadir métodos personalizados

    // Spring Data JPA puede crear automáticamente una consulta SQL por ti si
    // sigues una convención de nomenclatura específica:

    /**
     * Ejemplo de método personalizado para buscar productos cuyo nombre
     * contenga una cadena de texto (sin necesidad de escribir SQL).
     */
    // List<Product> findByNameContaining(String name);
}
