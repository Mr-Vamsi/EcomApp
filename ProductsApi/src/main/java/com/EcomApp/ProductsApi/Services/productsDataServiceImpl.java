package com.EcomApp.ProductsApi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.EcomApp.ProductsApi.Entities.productsData;
import com.EcomApp.ProductsApi.Repositories.productsDataRepository;

@Service
public class productsDataServiceImpl implements productsDataService{

    @Autowired
    private productsDataRepository repo;

    @Override
    public List<productsData> fetchAllProducts() {
        List<productsData> productsList =  repo.findAll();
        return productsList;
    }

    @SuppressWarnings("unused")
    @Override
    public productsData addProduct(productsData data) {
        productsData pdata = new productsData();
        pdata.setProductname(data.getProductname());
        pdata.setProductdescription(data.getProductdescription());
        pdata.setProductquantity(data.getProductquantity());
        pdata.setProductcost(data.getProductcost());
        productsData savedData = repo.save(pdata);
        if(savedData!=null){
            return savedData;
        }else{
            return null;
        }
    }
    
}
