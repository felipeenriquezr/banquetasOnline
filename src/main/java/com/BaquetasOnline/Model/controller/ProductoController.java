package com.BaquetasOnline.Model.controller;

import com.BaquetasOnline.Model.Producto;
import com.BaquetasOnline.Model.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/products")
    public String listarProducts(Model model) {
        List<Producto> products = productoRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
