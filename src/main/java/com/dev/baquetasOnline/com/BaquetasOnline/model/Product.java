package com.dev.baquetasOnline.com.BaquetasOnline.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// Si usas Lombok, importa esta línea:
import lombok.Data;

// Nota: si tu versión de Java/Spring usa 'javax.persistence' en lugar de 'jakarta.persistence',
// ajusta los imports en consecuencia.

@Entity // 👈 Anotación CLAVE: Marca esta clase como un objeto persistente mapeado a una tabla.
@Data   // 👈 Anotación de Lombok: Genera automáticamente getters, setters, toString, hashCode, y equals.
public class Product {

    // 1. Mapeo de la llave primaria (id_product)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que la BD genera el valor (auto-incremento)
    private Long idProduct;

    // 2. Mapeo de los demás campos de la tabla 'products'
    private String name;
    private String description;
    private Double price;

    // Usamos 'camelCase' en Java, que JPA generalmente mapea a 'snake_case'
    // (image_url) en la base de datos por convención.
    private String imageUrl;
    private String imageAlt;

    // Si NO usas Lombok, deberás escribir manualmente todos los métodos Getters, Setters,
    // y un constructor sin argumentos (vacío) aquí.
}
