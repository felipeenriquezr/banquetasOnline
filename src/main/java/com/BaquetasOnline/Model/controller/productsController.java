package com.BaquetasOnline.Model.controller;

import com.BaquetasOnline.Model.repository.productsRepository;
import org.hibernate.sql.results.jdbc.internal.JdbcValuesMappingProducerStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class productsController {
    @Autowired
    private productsRepository productsRepository;

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<JdbcValuesMappingProducerStandard> products = productsRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
