package com.onlineorder.controller;

import com.onlineorder.entity.MenuItemEntity;
import com.onlineorder.model.RestaurantDto;
import com.onlineorder.service.MenuItemService;
import com.onlineorder.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class MenuController {


    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;


    public MenuController(RestaurantService restaurantService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    // GetMapping 是get操作， this annotation is necessary for spring boot to connect to request from front end
    @GetMapping("/restaurant/{restaurantId}/menu") //{} 占位符 path variable must match with @PathVariable below
    public List<MenuItemEntity> getMenuByRestaurant(@PathVariable("restaurantId")  long restaurantId) {
        // @PathVariable 路径查询
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }


    @GetMapping("/restaurants/menu")
    public List<RestaurantDto> getMenuForAllRestaurants() {
        return restaurantService.getRestaurants();
    }
}




