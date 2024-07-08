package com.EcomApp.ProductsApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.EcomApp.ProductsApi.Entities.productsData;
import com.EcomApp.ProductsApi.Services.productsDataService;


@RestController
public class productsDataController {

    @Autowired
    private productsDataService service;

    @PostMapping("/fetchAllProducts")
    public List<productsData> fetchAllProducts(@RequestBody productsData data) {
        List<productsData> productsList =  service.fetchAllProducts();
        return productsList;
    }
    @PostMapping("/addProduct")
    public productsData addProduct(@RequestBody productsData data) {
        productsData pdata = service.addProduct(data);
        return pdata;
    }
    
}
