package com.EcomApp.cartApi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.EcomApp.cartApi.Entities.cart;
import com.EcomApp.cartApi.Repositories.cartRepositories;

@Service
public class cartServiceImpl implements cartService {

    @Autowired
    cartRepositories repositories;

    @SuppressWarnings("unused")
    @Override
    public cart addCart(cart cartData) {
         cart savedData = repositories.save(cartData);
         if(savedData!=null){
            return savedData;
         }else{
            return null;
         }
    }

    @Override
    public void deleteAllCartItems(cart cartData) {
        repositories.deleteUserCartData(cartData.getUseremail());
    }

    @Override
    public void removeCartItem(cart cartData) {
        long id = cartData.getId();
        repositories.deleteById(id);
    }

    @Override
    public List<cart> getAllCartItems(String email) {
        System.out.println("User email :- "+email);
       List<cart> cartList = repositories.findAllBy(email);
       System.out.println("Cart items list :-"+cartList);
       return cartList;
    }
}
