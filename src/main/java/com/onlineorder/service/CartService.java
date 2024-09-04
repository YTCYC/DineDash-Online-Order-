package com.onlineorder.service;


import com.onlineorder.entity.CartEntity;
import com.onlineorder.entity.MenuItemEntity;
import com.onlineorder.entity.OrderItemEntity;
import com.onlineorder.model.CartDto;
import com.onlineorder.model.OrderItemDto;
import com.onlineorder.repository.CartRepository;
import com.onlineorder.repository.MenuItemRepository;
import com.onlineorder.repository.OrderItemRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// TASKS
// 1. ADD MENU ID TO CART
// 2. DELETE MEUN ITEM FROM CART - FUTURE TASK
// 3. CLEAR CART, MIMIC CHECK OUT
// 4. VIEWING CART, - WHEN CLICKING CART, SHOWING EVERYTHING IN CART
// *//
@Service // spring boot use this annotation to simplifies management of dependency injection
public class CartService {


    private final CartRepository cartRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;


    public CartService(
            CartRepository cartRepository,
            MenuItemRepository menuItemRepository,
            OrderItemRepository orderItemRepository) {
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @CacheEvict(cacheNames = "cart", key = "#customerId")
    @Transactional // this annotation is to make sure the entire operation is atomic.
    // if any error is thrown, the previous database operations are rolled back
    // this is important whenever there are write operation to database
    public void addMenuItemToCart(long customerId, long menuItemId) {
        // get cart using customer id
        CartEntity cart = cartRepository.getByCustomerId(customerId);
        // get menu by menu item id
        MenuItemEntity menuItem = menuItemRepository.findById(menuItemId).get();
        OrderItemEntity orderItem = orderItemRepository.findByCartIdAndMenuItemId(cart.id(), menuItem.id());


        Long orderItemId;
        int quantity;


        if (orderItem == null) { // if NO ORDER ITEM FROM THIS USER YET
            orderItemId = null; // WHEN CREATE A NEW ENTITY, THIS WILL BE OR
            quantity = 1; // set quantity as 1
        } else { // if already has this item in cart
            orderItemId = orderItem.id();
            quantity = orderItem.quantity() + 1;
        }
        OrderItemEntity newOrderItem = new OrderItemEntity(orderItemId, menuItemId, cart.id(), menuItem.price(), quantity);
        orderItemRepository.save(newOrderItem); // in SQL, this is either update or insert
        // IF THIS IS NEW ORDER ITEM, THIS WILL ADD THIS NEW ORDER ITEM ENTITY INTO DB
        // IF THIS IS SAME ORDER ITEM, THIS WILL UPDATE THE DB ENTRY TO NEW QUANTITY
        cartRepository.updateTotalPrice(cart.id(), cart.totalPrice() + menuItem.price());
    }

    @Cacheable("cart")
    public CartDto getCart(Long customerId) {
        CartEntity cart = cartRepository.getByCustomerId(customerId);
        List<OrderItemEntity> orderItems = orderItemRepository.getAllByCartId(cart.id());
        List<OrderItemDto> orderItemDtos = getOrderItemDtos(orderItems);
        return new CartDto(cart, orderItemDtos);
    }

    @CacheEvict(cacheNames = "cart") // manually evict cache that should not be exist in cache anymore: for example, if i checked out my cart, no cart should be saved in cache
    @Transactional
    public void clearCart(Long customerId) {
        CartEntity cartEntity = cartRepository.getByCustomerId(customerId);
        orderItemRepository.deleteByCartId(cartEntity.id());
        cartRepository.updateTotalPrice(cartEntity.id(), 0.0);
    }


    private List<OrderItemDto> getOrderItemDtos(List<OrderItemEntity> orderItems) {
        Set<Long> menuItemIds = new HashSet<>();
        for (OrderItemEntity orderItem : orderItems) {
            menuItemIds.add(orderItem.menuItemId()); // GET ALL UNIQUE MENU ITEM ID
        }
        List<MenuItemEntity> menuItems = menuItemRepository.findAllById(menuItemIds);
        Map<Long, MenuItemEntity> menuItemMap = new HashMap<>();
        // LONG IS FOR MENU ID
        for (MenuItemEntity menuItem : menuItems) {
            menuItemMap.put(menuItem.id(), menuItem);
        }
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItemEntity orderItem : orderItems) {
            MenuItemEntity menuItem = menuItemMap.get(orderItem.menuItemId());
            OrderItemDto orderItemDto = new OrderItemDto(orderItem, menuItem);
            orderItemDtos.add(orderItemDto);
        }
        return orderItemDtos;
    }
}


