package com.EcomApp.cartApi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.EcomApp.cartApi.Entities.cart;

import jakarta.transaction.Transactional;

public interface cartRepositories extends JpaRepository<cart,Long>{    
    @Transactional
    @Modifying
    @Query("DELETE FROM cart u WHERE u.useremail = :email")
    int deleteUserCartData(String email);
    
    @Transactional
    @Modifying
    @Query("SELECT u FROM cart u WHERE u.useremail = :email")
    List<cart> findAllBy(String email);
    
}
