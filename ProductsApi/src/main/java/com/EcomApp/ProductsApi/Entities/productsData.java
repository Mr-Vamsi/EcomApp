package com.EcomApp.ProductsApi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class productsData{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long sku;
    private String productname;
    private String productdescription;
    private String productquantity;
    private String productcost;
}
