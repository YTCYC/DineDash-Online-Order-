package com.onlineorder.service;


import com.onlineorder.entity.MenuItemEntity;
import com.onlineorder.entity.RestaurantEntity;
import com.onlineorder.model.MenuItemDto;
import com.onlineorder.model.RestaurantDto;
import com.onlineorder.repository.MenuItemRepository;
import com.onlineorder.repository.RestaurantRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TASKS
// *//

@Service // spring boot uses this annotation to recognize the class
public class RestaurantService {
// this class uses two repositories

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

// constructor
    public RestaurantService(
            RestaurantRepository restaurantRepository,
            MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    // this function returns the list of restaurants with all the menu associated
    @Cacheable("restaurants") // cacheable does not have to be at this level, depends on functionality
    public List<RestaurantDto> getRestaurants() {
        // get all restaurant entities
        // 1. creat a mapping of restaurant id and their menu item
        // 2. create
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        // get all menu entities
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll();
        // create mapping of restaurant id as key, and a list of menuDTO as value
        Map<Long, List<MenuItemDto>> groupedMenuItems = new HashMap<>();
        // loop thru menu entity
        for (MenuItemEntity menuItemEntity : menuItemEntities) {
            // menuItemEntity.restaurantId() returns restaurant id, which is the key of Map,
            // if this key is not exist in map yet,
            // creat an empty list, that associated with this restaurant id
            // if key is exist, then return existing value associated with this key
            List<MenuItemDto> menuGroup = groupedMenuItems.computeIfAbsent(menuItemEntity.restaurantId(), k -> new ArrayList<>());
            MenuItemDto menuItemDto = new MenuItemDto(menuItemEntity);
            // add this new menu dto to this restaurant id
            menuGroup.add(menuItemDto);
        }
        List<RestaurantDto> results = new ArrayList<>();
        for (RestaurantEntity restaurantEntity : restaurantEntities) {
            // restaurantEntity.id() is the key, which is restaurant id
            // groupedMenuItems.get(restaurantEntity.id()), this returns list of menu DTO associated with this restaurant id
            RestaurantDto restaurantDto = new RestaurantDto(restaurantEntity, groupedMenuItems.get(restaurantEntity.id()));
            results.add(restaurantDto);
        }
        return results;
    }
}
