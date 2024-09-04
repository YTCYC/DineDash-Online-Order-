package com.onlineorder.repository;


import com.onlineorder.entity.MenuItemEntity;
import org.springframework.data.repository.ListCrudRepository;


import java.util.List;

public interface MenuItemRepository extends ListCrudRepository<MenuItemEntity, Long> {
    // in this < MenuItemEntity, Long>, long is menu id data type
    // in all ListCrudRepository method, when passing ID, it's menu item id

    // SELECT * FROM menu_items WHERE restaurant_id = :restaurantId
    List<MenuItemEntity> getByRestaurantId(Long restaurantId);
}
