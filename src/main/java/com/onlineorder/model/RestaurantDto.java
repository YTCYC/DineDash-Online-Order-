package com.onlineorder.model;


import com.onlineorder.entity.RestaurantEntity;


import java.util.List;

// we also need all menuItems for this restaurant, which is not defined in
// entity so we need DTO to help us combine this data for each restaurant
public record RestaurantDto(
        Long id,
        String name,
        String address,
        String phone,
        String imageUrl,
        List<MenuItemDto> menuItems
        // we get list of menu items for each restaurant in restaurant service
) {


    public RestaurantDto(RestaurantEntity entity, List<MenuItemDto> menuItems) {
        this(entity.id(), entity.name(), entity.address(), entity.phone(), entity.imageUrl(), menuItems);
    }
}

