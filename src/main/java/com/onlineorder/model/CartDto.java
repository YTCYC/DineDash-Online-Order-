package com.onlineorder.model;

// DTO data transfer object
// we use DTO to organize, transfer and protect raw data

import com.onlineorder.entity.CartEntity;


import java.util.List;


public record CartDto(
        Long id, // this is cart id
        Double totalPrice, //
        List<OrderItemDto> orderItems
) {
    // this constructor is easier for us to construct this class in the future
    // this is secondary constructor
    public CartDto(CartEntity entity, List<OrderItemDto> orderItems) {
        this(entity.id(), entity.totalPrice(), orderItems);
        // we will create orderItems later
    }
}
