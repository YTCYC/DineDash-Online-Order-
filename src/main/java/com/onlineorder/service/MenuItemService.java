package com.onlineorder.service;


import com.onlineorder.entity.MenuItemEntity;
import com.onlineorder.repository.MenuItemRepository;
import org.springframework.stereotype.Service;


import java.util.List;

// TASKS
// LIST ALL MENU FOR A RESTAURANT WHEN GIVEN A RESTAURANT ID
// GET ONE SPECIFIC MENU INFO WHEN GIVEN A MENU ID, THIS IS DISH ID NOT THE ENTIRE MENU*//
@Service
public class MenuItemService {


    private final MenuItemRepository menuItemRepository;


    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }


    public List<MenuItemEntity> getMenuItemsByRestaurantId(long restaurantId) {
        // get all menu item entity{id, restaurant id, name, description, price, imageUrl} for given restaurant id
        return menuItemRepository.getByRestaurantId(restaurantId);
        // List<MenuItemEntity> getByRestaurantId(Long restaurantId); is defined in menuItemRepository
    }

    public MenuItemEntity getMenuItemById(long menuId) {
        // get menu item entity thru giving menu id
        return menuItemRepository.findById(menuId).get();
    }
}
