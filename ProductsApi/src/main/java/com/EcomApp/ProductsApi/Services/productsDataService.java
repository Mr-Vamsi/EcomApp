package com.EcomApp.ProductsApi.Services;

import java.util.List;

import com.EcomApp.ProductsApi.Entities.productsData;

public interface productsDataService {

    List<productsData> fetchAllProducts();

    productsData addProduct(productsData data);    

}
