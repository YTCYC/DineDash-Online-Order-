package com.onlineorder.model;


import com.onlineorder.entity.MenuItemEntity;

// we do not need restaurant id in this DTO? why?
public record MenuItemDto(
        Long id,
        String name,
        String description,
        Double price,
        String imageUrl
) {


    public MenuItemDto(MenuItemEntity entity) {
        this(entity.id(), entity.name(), entity.description(), entity.price(), entity.imageUrl());
    }
}
