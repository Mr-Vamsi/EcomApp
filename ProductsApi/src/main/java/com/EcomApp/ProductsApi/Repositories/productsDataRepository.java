package com.EcomApp.ProductsApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EcomApp.ProductsApi.Entities.productsData;

@Repository
public interface productsDataRepository extends JpaRepository<productsData , Long> {
    
}
