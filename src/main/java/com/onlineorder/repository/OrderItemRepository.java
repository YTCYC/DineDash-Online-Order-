package com.onlineorder.repository;


import com.onlineorder.entity.OrderItemEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;


import java.util.List;


public interface OrderItemRepository extends ListCrudRepository<OrderItemEntity, Long> {

    // SELECT * FROM order_items WHERE cart_id = :cartId
    List<OrderItemEntity> getAllByCartId(Long cartId);

    // SELECT * FROM order_items WHERE cart_id = :cartId AND menu_item_id = :menuItemId
    OrderItemEntity findByCartIdAndMenuItemId(Long cartId, Long menuItemId);


    @Modifying
    @Query("DELETE FROM order_items WHERE cart_id = :cartId")
    void deleteByCartId(Long cartId);
}
