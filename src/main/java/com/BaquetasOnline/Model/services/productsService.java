package com.BaquetasOnline.Model.services;

import com.BaquetasOnline.Model.models.productsModel;
import com.BaquetasOnline.Model.repository.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service


public class productsService {
    @Autowired
    productsRepository productsRepository;

    public ArrayList<productsModel> getProducts() {
        return (ArrayList<productsModel>) productsRepository.findAll();
    }
    public productsModel saveProduct(productsModel product) {
        return productsRepository.save(product);
    }

}
