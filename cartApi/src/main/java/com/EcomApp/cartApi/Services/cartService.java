package com.EcomApp.cartApi.Services;

import java.util.List;

import com.EcomApp.cartApi.Entities.cart;
public interface cartService {

    cart addCart(cart cartData);

    void deleteAllCartItems(cart cartData);

    void removeCartItem(cart cartData);

    List<cart> getAllCartItems(String email);
    
}
