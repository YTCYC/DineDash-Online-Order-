package com.onlineorder.service;


import com.onlineorder.entity.CartEntity;
import com.onlineorder.entity.CustomerEntity;
import com.onlineorder.repository.CartRepository;
import com.onlineorder.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//* TASKS
// SIGN UP, GIVEN NAME AND EMAIL, GENERATE AN ID AND SAVE INTO DB. AT THE SAME TIME CREAT A NEW CART FOR THIS USER ID AND SAVE INTO DB
// GET ALL CUSTOMER INFO WITH GIVEN EMAIL
// SIGN IN AND SIGN OUT ARE PROVIDED BY SPRINGBOOT
// *//
@Service
public class CustomerService {


    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;


    public CustomerService(
            CartRepository cartRepository,
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder, // dependency not by me, but by spring boot
            UserDetailsManager userDetailsManager // dependency not by me, but by spring boot
    ) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }




    @Transactional // atomic， there are few write operations
    public void signUp(String email, String password, String firstName, String lastName) {
        email = email.toLowerCase();
        // builder pattern == new keyWord
        // UserDetails user = new U
        // UserDetails is an interface from spring boot security package
        UserDetails user = User.builder() // UserDetails is a class defined in spring security
                // this is using builder pattern to new an UserDetails object
                .username(email)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        // Spring Security does not support first and last name so we use customerRepository to update them
        customerRepository.updateNameByEmail(email, firstName, lastName); // write data to database

        // customer and cart is one to one relationship, we need to creat a new cart for new customer
        CustomerEntity newCustomer = customerRepository.findByEmail(email);
        CartEntity cart = new CartEntity(null, newCustomer.id(), 0.0);
        cartRepository.save(cart);
    }

    public CustomerEntity getCustomerByEmail(String email) {
        // this is public method
        return customerRepository.findByEmail(email);
    }
}

// we don't need sign in and sign out because this is provided by spring security

