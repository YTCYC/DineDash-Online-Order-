package com.onlineorder.repository;

import com.onlineorder.entity.RestaurantEntity;
import org.springframework.data.repository.ListCrudRepository;


public interface RestaurantRepository extends ListCrudRepository<RestaurantEntity, Long> {
    //no need to define new method, the methods from ListCrudRepository is sufficient
}

