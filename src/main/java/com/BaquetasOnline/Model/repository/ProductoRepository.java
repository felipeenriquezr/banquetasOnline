package com.BaquetasOnline.Model.repository;

import com.BaquetasOnline.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
