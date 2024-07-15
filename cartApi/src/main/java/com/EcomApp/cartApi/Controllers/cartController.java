package com.EcomApp.cartApi.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.EcomApp.cartApi.Entities.cart;
import com.EcomApp.cartApi.Services.cartService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class cartController {
    HttpSession session;
    @Autowired
    private cartService service;

    @PostMapping("/addToCart")
    public cart addToCart(@RequestBody cart cartData) {
            cart cData = service.addCart(cartData);
            return cData;
    }
    @PostMapping("/getAllCartItems")
    public List<cart> getAllCartItems(@RequestBody cart cartData) {
        List<cart> cData = service.getAllCartItems(cartData.getUseremail());
        return cData;
    }
    
    @PostMapping("/buy")
    public void buyProduct(@RequestBody cart cartData) {
        service.deleteAllCartItems(cartData);
    }
    @PostMapping("/removeCartItem")
    public void removeCartItem(@RequestBody cart cartData) {
        service.removeCartItem(cartData);
    }
    @PostMapping("/deleteAllCartItems")
    public void deleteCartItems(@RequestBody cart cartData) {
        service.deleteAllCartItems(cartData);
    }
    
}
