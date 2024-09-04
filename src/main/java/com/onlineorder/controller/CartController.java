package com.onlineorder.controller;


import com.onlineorder.entity.CustomerEntity;
import com.onlineorder.model.AddToCartBody;
import com.onlineorder.model.CartDto;
import com.onlineorder.service.CartService;
import com.onlineorder.service.CustomerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController // signifies this is for RestAPI methodology
public class CartController {


    private final CartService cartService;
    private final CustomerService customerService;


    public CartController(
            CartService cartService,
            CustomerService customerService
    ) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

//    @GetMapping("/cart")
//    public CartDto getCart() {
//        return cartService.getCart(1L);
    // later we will create customer controller so for now we use a placeholder id which is hard coded
    //        //
//    }
    // @AuthenticationPrincipal is provided by spring to tell us who is calling this endpoint
    @GetMapping("/cart")
    public CartDto getCart(@AuthenticationPrincipal User user) {
        CustomerEntity customer = customerService.getCustomerByEmail(user.getUsername());
        return cartService.getCart(customer.id());
    }


    // here we are sending write request so we use PostMapping
//    @PostMapping("/cart")
//    public void addToCart(@RequestBody AddToCartBody body) {
//        cartService.addMenuItemToCart(1L, body.menuId());
//    }
    // when use RequestBody?
    // when need passing in parameters
    // when need to protect sensitive info

    @PostMapping("/cart")
    public void addToCart(@AuthenticationPrincipal User user, @RequestBody AddToCartBody body) {
        CustomerEntity customer = customerService.getCustomerByEmail(user.getUsername());
        cartService.addMenuItemToCart(customer.id(), body.menuId());
    }



//    @PostMapping("/cart/checkout")
//    public void checkout() {
//        cartService.clearCart(1L);
//    }
    @PostMapping("/cart/checkout")
    public void checkout(@AuthenticationPrincipal User user) {
        CustomerEntity customer = customerService.getCustomerByEmail(user.getUsername());
        cartService.clearCart(customer.id());
    }

}

