package com.onlineorder.model;


import com.onlineorder.entity.MenuItemEntity;
import com.onlineorder.entity.OrderItemEntity;

// for an orderitem, we also want to know additional information like menu item
public record OrderItemDto( // in this order item, we need info from cart and menu
        Long orderItemId,  // from OrderItemEntity
        Long menuItemId,  // from OrderItemEntity
        Long restaurantId,   // from MenuItemEntity,
        Double price,   // from OrderItemEntity
        Integer quantity, // from OrderItemEntity
        String menuItemName,   // from MenuItemEntity,
        String menuItemDescription,  // from MenuItemEntity,
        String menuItemImageUrl   // from MenuItemEntity,
) {
    public OrderItemDto(OrderItemEntity orderItemEntity, MenuItemEntity menuItemEntity) {
        this(
                orderItemEntity.id(), // orderItemId = id defined in orderitemEntity
                orderItemEntity.menuItemId(),
                menuItemEntity.restaurantId(),
                orderItemEntity.price(),
                orderItemEntity.quantity(),
                menuItemEntity.name(),
                menuItemEntity.description(),
                menuItemEntity.imageUrl()
        );
    }
}
