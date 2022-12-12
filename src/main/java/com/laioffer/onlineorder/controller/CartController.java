package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET) // Get method 需要返回值
    @ResponseBody // 所以需要response body to change to JSON format
    public Cart getCart(){

        return cartService.getCart();
    }

}
